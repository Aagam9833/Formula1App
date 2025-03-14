package com.aagamshah.slipstreampicks.presentation.imageuploadscreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.aagamshah.slipstreampicks.navigation.Route

fun NavGraphBuilder.imageUploadScreenRoute(navController: NavHostController) {
    composable(Route.ImageUploadScreen.route) {
        ImageUploadScreen(navController)
    }
}