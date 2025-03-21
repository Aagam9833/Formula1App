package com.aagamshah.slipstreampicks.domain.model.request

data class SaveFantasyTeamRequestModel(
    val user_id: String,
    val driver_1_id: String,
    val driver_2_id: String,
    val driver_3_id: String,
    val driver_4_id: String,
    val driver_5_id: String,
    val constructor_id: String
)
