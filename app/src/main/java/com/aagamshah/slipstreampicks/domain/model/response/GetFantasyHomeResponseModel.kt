package com.aagamshah.slipstreampicks.domain.model.response

data class GetFantasyHomeResponseModel(
    val team: List<AllConstructor>,
    val totalPoints: Long,
    val allDrivers: List<AllConstructor>,
    val allConstructors: List<AllConstructor>
)

data class AllConstructor(
    val id: String,
    val imageUrl: String,
    val type: String,
    val name: String? = null
)
