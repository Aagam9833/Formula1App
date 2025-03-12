package com.aagamshah.slipstreampicks.utils

import android.content.Context
import com.aagamshah.slipstreampicks.data.local.sharedpreferences.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = PreferenceManager(context).getAccessToken()

        val request = chain.request().newBuilder().apply {
            if (!token.isNullOrEmpty()) {
                addHeader("Authorization", "Bearer $token")
            }
        }.build()

        return chain.proceed(request)
    }
}
