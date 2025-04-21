package com.example.assignment.feature.commerce

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.assignment.domain.usecase.GetSectionProductListUseCase
import com.example.assignment.domain.usecase.ObserveLikedIdsUseCase
import com.example.assignment.domain.usecase.ToggleLikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CommerceViewModel @Inject constructor(
    getSectionProductListUseCase: GetSectionProductListUseCase,
    observeLikedIdsUseCase: ObserveLikedIdsUseCase,
    private val toggleLikeUseCase: ToggleLikeUseCase,
) : ViewModel() {

    private val _errorChannel: Channel<Throwable> = Channel()
    val errorChannel = _errorChannel.receiveAsFlow()

    private val likedIdsFlow = observeLikedIdsUseCase()
        .catch { throwable ->
            sendError(throwable)
            emit(emptySet())
        }

    val sectionProductsState = getSectionProductListUseCase()
        .cachedIn(viewModelScope)
        .combine(likedIdsFlow) { pagingData, likedIds ->
            pagingData.map { section ->
                section.toModel(likedIds = likedIds)
            }
        }

    fun toggleLike(productId: Long) {
        viewModelScope.launch {
            toggleLikeUseCase(productId = productId)
                .onFailure { throwable ->
                    sendError(throwable = throwable)
                }
        }
    }

    fun sendError(throwable: Throwable) {
        _errorChannel.trySend(throwable)
    }
}