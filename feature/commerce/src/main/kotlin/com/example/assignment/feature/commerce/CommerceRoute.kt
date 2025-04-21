package com.example.assignment.feature.commerce

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun CommerceRoute() {
    val viewModel: CommerceViewModel = hiltViewModel()
    val sectionProductModel = viewModel.sectionProductsState.collectAsLazyPagingItems()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.errorChannel.collect { throwable ->
            Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
        }
    }

    CommerceScreen(
        sectionProductModel = sectionProductModel,
        onLikedUpdate = viewModel::toggleLike,
        onError = viewModel::sendError
    )
}