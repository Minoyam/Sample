package com.example.assignment.domain.usecase

import com.example.assignment.domain.repository.Repository
import javax.inject.Inject

class ToggleLikeUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(productId: Long): Result<Unit> {
        return repository.toggleLike(productId = productId)
    }
}