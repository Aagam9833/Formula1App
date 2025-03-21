package com.aagamshah.slipstreampicks.presentation.fantasyscreen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.slipstreampicks.domain.model.response.CurrentConstructor
import com.aagamshah.slipstreampicks.domain.model.response.CurrentDriver
import com.aagamshah.slipstreampicks.domain.model.response.FantasyTeam
import com.aagamshah.slipstreampicks.domain.model.response.GetFantasyHomeResponseModel
import com.aagamshah.slipstreampicks.domain.usecase.FantasyHomeUseCase
import com.aagamshah.slipstreampicks.presentation.components.CardType
import com.aagamshah.slipstreampicks.presentation.components.FantasyCardModel
import com.aagamshah.slipstreampicks.utils.Constants
import com.aagamshah.slipstreampicks.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FantasyViewModel @Inject constructor(
    private val fantasyHomeUseCase: FantasyHomeUseCase
) : ViewModel() {

    private val _isLoading = mutableStateOf<Boolean>(false)
    val isLoading: Boolean get() = _isLoading.value

    private val _homeData = mutableStateOf<GetFantasyHomeResponseModel?>(null)
    val homeData: GetFantasyHomeResponseModel? get() = _homeData.value

    private val _currentUserTeam = mutableStateListOf<FantasyCardModel>()
    val currentUserTeam: List<FantasyCardModel?> get() = _currentUserTeam

    init {
        getFantasyHome()
    }

    fun saveFantasyTeam(data: List<FantasyCardModel>) {
        viewModelScope.launch {
            fantasyHomeUseCase.invoke(data).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _isLoading.value = false
                        Log.d(Constants.TAG, result.message ?: "Something went wrong")
                    }

                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Success -> {
                        _isLoading.value = false
                        getFantasyHome()
                    }
                }
            }.collect()
        }
    }

    private fun getFantasyHome() {
        viewModelScope.launch {
            _currentUserTeam.clear()
            fantasyHomeUseCase.invoke().onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _isLoading.value = false
                        Log.d(Constants.TAG, result.message ?: "Something went wrong")
                    }

                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Success -> {
                        _isLoading.value = false
                        result.data?.let {
                            _homeData.value = it
                            if (it.currentUser == null) {
                                setCurrentUserFantasyTeam(
                                    null,
                                    it.currentDrivers,
                                    it.currentConstructors
                                )
                            } else {

                                setCurrentUserFantasyTeam(
                                    it.currentUser.fantasy_team,
                                    it.currentDrivers,
                                    it.currentConstructors
                                )
                            }

                        }
                    }
                }
            }.collect()
        }
    }

    private fun setCurrentUserFantasyTeam(
        fantasyTeam: FantasyTeam?,
        currentDrivers: List<CurrentDriver>,
        currentConstructors: List<CurrentConstructor>
    ) {

        val userDrivers = fantasyTeam?.let {
            listOf(
                it.driver_1_id,
                it.driver_2_id,
                it.driver_3_id,
                it.driver_4_id,
                it.driver_5_id
            ).mapNotNull { driverId ->
                currentDrivers.find { driver -> driver.driverId == driverId }?.let { driver ->
                    FantasyCardModel(driver.name, driver.imageUrl, driver.driverId, CardType.DRIVER)
                }
            }
        } ?: emptyList()

        _currentUserTeam.addAll(userDrivers)

        fantasyTeam?.constructor_id?.let { constructorId ->
            currentConstructors.find { it.constructorId == constructorId }?.let {
                _currentUserTeam.add(
                    FantasyCardModel(
                        it.name,
                        it.imageUrl,
                        it.constructorId,
                        CardType.CONSTRUCTOR
                    )
                )
            }
        }
    }


}