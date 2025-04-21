package com.example.assignment.domain.usecase

import com.example.assignment.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveLikedIdsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(
    ): Flow<Set<Long>> {
        return repository.observeLikedIds()
    }
}