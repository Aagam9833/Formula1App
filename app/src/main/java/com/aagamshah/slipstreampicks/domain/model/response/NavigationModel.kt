package com.aagamshah.slipstreampicks.domain.model.response

data class NavigationModel(
    val tabs: List<NavigationModelElement>?,
)

data class NavigationModelElement(
    val id: String,
    val enabled: Boolean,
)
