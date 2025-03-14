package com.aagamshah.slipstreampicks.utils

import android.util.Base64
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.nio.charset.Charset

object JwtUtils {

    private data class JwtPayload(
        @SerializedName("exp") val exp: Long
    )

    private fun getTokenExpiry(jwt: String): Long? {
        return try {
            val payload = jwt.split(".")[1]
            val decoded = Base64.decode(payload, Base64.URL_SAFE)
            val json = String(decoded, Charset.defaultCharset())
            val jwtPayload = Gson().fromJson(json, JwtPayload::class.java)
            jwtPayload?.exp?.times(1000) // Convert seconds to milliseconds
        } catch (e: Exception) {
            null
        }
    }

    fun isTokenExpired(jwt: String): Boolean {
        val expiry = getTokenExpiry(jwt) ?: return true
        return System.currentTimeMillis() > expiry
    }
}
