package com.aagamshah.slipstreampicks.domain.model.response

import com.aagamshah.slipstreampicks.presentation.components.CardType
import com.aagamshah.slipstreampicks.presentation.components.FantasyCardModel

data class GetFantasyHomeResponseModel(
    val topRanks: List<CurrentUser>,
    val currentUser: CurrentUser?,
    val currentDrivers: List<CurrentDriver>,
    val currentConstructors: List<CurrentConstructor>
)

data class CurrentConstructor(
    val constructorId: String,
    val name: String,
    val imageUrl: String
)

data class CurrentDriver(
    val driverId: String,
    val name: String,
    val imageUrl: String,
    val permanentNumber: String
)

data class CurrentUser(
    val user_id: String,
    val username: String,
    val profile_image: String,
    val points: Long,
    val rank: Long,
    val fantasy_team: FantasyTeam?
)

data class FantasyTeam(
    val driver_1_id: String,
    val driver_2_id: String,
    val driver_3_id: String,
    val driver_4_id: String,
    val driver_5_id: String,
    val constructor_id: String
)

fun List<CurrentDriver>?.toDriverFantasyCardList(): List<FantasyCardModel> {
    return this?.map {
        FantasyCardModel(
            title = it.name,
            image = it.imageUrl,
            id = it.driverId,
            type = CardType.DRIVER
        )
    } ?: emptyList()
}

fun List<CurrentConstructor>?.toConstructorFantasyCardList(): List<FantasyCardModel> {
    return this?.map {
        FantasyCardModel(
            title = it.name,
            image = it.imageUrl,
            id = it.constructorId,
            type = CardType.CONSTRUCTOR
        )
    } ?: emptyList()
}
