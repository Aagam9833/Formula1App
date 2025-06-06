package com.aagamshah.slipstreampicks.utils

object Constants {

    //API CONSTANTS
    const val TAG = "TAGGED"

    const val BASE_URL = "https://formula1server.onrender.com/"

    private const val VERSION_V1 = "api/v1"
    private const val AUTH = "auth"

    const val SIGN_UP = "${AUTH}/signup"
    const val LOGIN = "${AUTH}/login"
    const val FORGOT_PASSWORD = "${AUTH}/forgot-password"
    const val RESET_PASSWORD = "${AUTH}/reset-password"

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