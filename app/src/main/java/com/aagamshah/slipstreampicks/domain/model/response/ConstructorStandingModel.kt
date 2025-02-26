package com.aagamshah.slipstreampicks.domain.model.response

data class ConstructorStandingModel(
    val title: String,
    val constructors: List<ConstructorStandingModelElement>?,
)

data class ConstructorStandingModelElement(
    val position: String,
    val points: String,
    val imageUrl: String,
    val constructorId: String,
    val constructorName: String,
    val constructorNationality: String,
    val constructorColor: String,
)
