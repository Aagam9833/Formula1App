package com.aagamshah.slipstreampicks.domain.model.response

data class DriverStandingModel(
    val title: String,
    val drivers: List<DriverStandingModelElement>?,
)

data class DriverStandingModelElement(
    val position: String,
    val points: String,
    val name: String,
    val imageUrl: String,
    val driverId: String,
    val constructorId: String,
    val permanentNumber: String,
    val constructorName: String,
    val constructorColor: String,
)
