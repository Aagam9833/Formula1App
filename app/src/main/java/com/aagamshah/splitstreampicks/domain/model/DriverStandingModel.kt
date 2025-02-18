package com.aagamshah.splitstreampicks.domain.model

data class DriverStandingModel(
    val title: String,
    val drivers: List<DriverStandingModelElement>,
)

data class DriverStandingModelElement(
    val position: String,
    val points: String,
    val name: String,
    val driverID: String,
    val constructorID: String,
    val constructorName: String,
    val constructorColor: String,
)
