package com.example.assignment.domain.usecase

import androidx.paging.PagingData
import com.example.assignment.domain.model.SectionProduct
import com.example.assignment.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSectionProductListUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(
    ): Flow<PagingData<SectionProduct>> {
        return repository.getSectionProducts()
    }
}