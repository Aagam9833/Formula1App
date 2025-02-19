package com.aagamshah.slipstreampicks.domain.model

data class NavigationModel(
    val tabs: List<NavigationModelElement>?,
)

data class NavigationModelElement(
    val id: String,
    val enabled: Boolean,
)
