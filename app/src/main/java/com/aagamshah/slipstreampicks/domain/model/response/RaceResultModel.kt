package com.aagamshah.slipstreampicks.domain.model.response

data class RaceResultModel(
    val round: String,
    val raceName: String,
    val results: List<Result>,
)

data class Result(
    val position: String,
    val driverId: String,
    val code: String,
    val name: String,
    val points: String,
    val driverImage: String,
    val constructorName: String,
    val constructorColor: String,
    val grid: String,
    val status: String,
    val fastestLapTime: String,
    val avgSpeed: String,
)

