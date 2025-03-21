package com.aagamshah.slipstreampicks.utils

object Constants {

    //API CONSTANTS
    private const val isDebug = false
    const val TAG = "TAGGED"

    val BASE_URL =
        if (isDebug) "http://192.168.204.204:5000/" else "https://formula1server.onrender.com/"

    private const val VERSION_V1 = "api/v1"
    private const val AUTH = "auth"

    const val SIGN_UP = "${AUTH}/signup"
    const val LOGIN = "${AUTH}/login"

    const val GET_HOME = "$VERSION_V1/home"
    const val GET_DRIVER_STANDING = "${VERSION_V1}/currentDriverStandings"
    const val GET_CONSTRUCTOR_STANDINGS = "${VERSION_V1}/currentConstructorStandings"
    const val NAVIGATION = "${VERSION_V1}/navigation"
    const val CURRENT_SEASON = "${VERSION_V1}/currentSeasonRaces"
    const val GET_ROUND_RESULT = "${VERSION_V1}/raceResults"
    const val GET_FANTASY_HOME = "${VERSION_V1}/fantasy-home"
    const val SAVE_FANTASY_TEAM = "${VERSION_V1}/save-fantasy-team"
    const val UPLOAD_PROFILE_IMAGE = "${VERSION_V1}/upload-profile"

    //APP CONSTANTS

}