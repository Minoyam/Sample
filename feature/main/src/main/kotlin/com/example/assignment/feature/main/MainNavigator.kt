package com.example.assignment.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.assignment.core.model.Route

internal class MainNavigator(
    val navController: NavHostController,
) {
    val startDestination = Route.Commerce::class
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator {
    return remember(navController) {
        MainNavigator(navController = navController)
    }
}
