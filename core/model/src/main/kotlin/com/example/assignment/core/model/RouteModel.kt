package com.example.assignment.core.model

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Commerce
}