package com.aagamshah.slipstreampicks.domain.model

data class CurrentSeasonModel(
    val pastRaces: List<Race>?,
    val upcomingRaces: List<Race>?,
)

data class Race(
    val raceName: String,
    val round: String,
    val location: Location,
    val circuit: Circuit,
    val sessions: Sessions,
)

data class Sessions(
    val race: SessionDetail,
    val firstPractice: SessionDetail,
    val secondPractice: SessionDetail? = null,
    val thirdPractice: SessionDetail? = null,
    val qualifying: SessionDetail,
    val sprint: SessionDetail? = null,
    val sprintQualifying: SessionDetail? = null,
)

data class SessionDetail(
    val date: String,
    val time: String,
)