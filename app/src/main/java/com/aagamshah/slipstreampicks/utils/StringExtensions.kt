package com.aagamshah.slipstreampicks.utils

object StringExtensions {

    fun String.isValidEmail(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

}