package com.aagamshah.splitstreampicks.common

object Constants {

    //API CONSTANTS
    private const val isDebug = false
    const val TAG = "TAGGED"

    val BASE_URL =
        if (isDebug) "http://192.168.204.204:5000/" else "https://formula1server.onrender.com/"

    private const val VERSION = "api/v1"

    const val GET_HOME = "$VERSION/home"
    const val GET_DRIVER_STANDING = "${VERSION}/currentDriverStandings"

    //APP CONSTANTS


}