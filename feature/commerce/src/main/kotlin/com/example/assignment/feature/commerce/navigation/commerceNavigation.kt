package com.example.assignment.feature.commerce.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.assignment.feature.commerce.CommerceRoute
import com.example.assignment.core.model.Route

fun NavGraphBuilder.commerceNavGraph() {
    composable<Route.Commerce> {
        CommerceRoute()
    }
}