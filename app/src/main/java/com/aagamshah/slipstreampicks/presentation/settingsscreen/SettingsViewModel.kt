package com.aagamshah.slipstreampicks.presentation.settingsscreen

import android.app.Application
import android.os.Build
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.slipstreampicks.data.local.sharedpreferences.PreferenceManager
import com.aagamshah.slipstreampicks.domain.model.local.User
import com.aagamshah.slipstreampicks.domain.usecase.ClearUserUseCase
import com.aagamshah.slipstreampicks.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userUseCase: ClearUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val preferenceManager: PreferenceManager,
    application: Application
) : ViewModel() {

    private val _user = mutableStateOf<User?>(null)
    val user: User? get() = _user.value

    private val versionName: String
    private val versionCode: String
    val versionText: String

    init {

        val packageInfo = application.packageManager.getPackageInfo(application.packageName, 0)
        versionName = packageInfo.versionName.toString()
        versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            packageInfo.longVersionCode.toInt().toString()
        } else {
            ""
        }
        versionText = "Version : $versionName-$versionCode"

        viewModelScope.launch {
            _user.value = getUserUseCase.invoke()
        }
    }

    fun logoutUser() {
        viewModelScope.launch {
            userUseCase.invoke()
            preferenceManager.clearAccessToken()
        }
    }


}