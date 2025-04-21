package com.example.assignment.data.repository

import androidx.paging.PagingData
import com.example.assignment.data.source.RemoteDataSource
import com.example.assignment.data.toDomain
import com.example.assignment.datastore.LikeDataStore
import com.example.assignment.domain.model.SectionProduct
import com.example.assignment.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val likeDataStore: LikeDataStore
) : Repository {
    override fun getSectionProducts(): Flow<PagingData<SectionProduct>> =
        remoteDataSource.getSectionProducts().toDomain()

    override fun observeLikedIds(): Flow<Set<Long>> = likeDataStore.observeLikedIds()

    override suspend fun toggleLike(productId: Long): Result<Unit> =
        likeDataStore.toggle(productId = productId)
}