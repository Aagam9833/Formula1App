package com.aagamshah.slipstreampicks.common

object Constants {

    //API CONSTANTS
    private const val isDebug = false
    const val TAG = "TAGGED"

    val BASE_URL =
        if (isDebug) "http://192.168.204.204:5000/" else "https://formula1server.onrender.com/"

    private const val VERSION_V1 = "api/v1"

    const val GET_HOME = "$VERSION_V1/home"
    const val GET_DRIVER_STANDING = "${VERSION_V1}/currentDriverStandings"
    const val GET_CONSTRUCTOR_STANDINGS = "${VERSION_V1}/currentConstructorStandings"
    const val NAVIGATION = "${VERSION_V1}/navigation"
    const val CURRENT_SEASON = "${VERSION_V1}/currentSeasonRaces"
    const val GET_ROUND_RESULT = "${VERSION_V1}/raceResults"

    //APP CONSTANTS

}