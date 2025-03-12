package com.aagamshah.slipstreampicks.utils

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException

object ApiExceptionHandler {

    fun handleException(exception: Throwable): String {
        return when (exception) {
            is SocketTimeoutException -> "Request timed out. Please try again."
            is IOException -> "Check your internet connection."
            is HttpException -> {
                val errorBody = exception.response()?.errorBody()?.string()
                parseErrorMessage(errorBody) ?: getHttpErrorMessage(exception.code())
            }

            else -> "An unexpected error occurred."
        }
    }

    private fun parseErrorMessage(errorBody: String?): String? {
        return try {
            errorBody?.let {
                val errorResponse = Gson().fromJson(it, ApiErrorResponse::class.java)
                errorResponse.errorMessage
            }
        } catch (e: Exception) {
            null
        }
    }

    private fun getHttpErrorMessage(code: Int): String {
        return when (code) {
            400 -> "Bad Request. Please try again."
            401 -> "Invalid email/username or password."
            403 -> "You don’t have permission to access this resource."
            404 -> "Requested resource not found."
            500 -> "Server error. Please try again later."
            else -> "Something went wrong. Please try again."
        }
    }

    data class ApiErrorResponse(
        @SerializedName("error") val errorMessage: String?
    )
}