package com.aagamshah.splitstreampicks.domain.model

data class NavigationModel(
    val tabs: List<NavigationModelElement>?,
)

data class NavigationModelElement(
    val id: String,
    val enabled: Boolean,
)
