package com.aagamshah.splitstreampicks.domain.model

data class HomeModel(
    val raceName: String,
    val location: Location,
    val circuit: Circuit,
    val nextSession: NextSession,
)

data class Circuit(
    val id: String,
    val name: String,
    val url: String,
    val outlineUrl: String,
)

data class Location(
    val lat: String,
    val long: String,
    val locality: String,
    val country: String,
)

data class NextSession(
    val name: String,
    val date: String,
    val time: String,
)

