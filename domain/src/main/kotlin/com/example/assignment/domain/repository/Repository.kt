package com.example.assignment.domain.repository

import androidx.paging.PagingData
import com.example.assignment.domain.model.SectionProduct
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getSectionProducts(): Flow<PagingData<SectionProduct>>
    fun observeLikedIds(): Flow<Set<Long>>
    suspend fun toggleLike(productId: Long) : Result<Unit>
}