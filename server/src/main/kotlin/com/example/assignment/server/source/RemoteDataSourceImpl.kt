package com.example.assignment.server.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.assignment.data.model.SectionProductData
import com.example.assignment.data.source.RemoteDataSource
import com.example.assignment.server.Service
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class RemoteDataSourceImpl @Inject constructor(
    private val service: Service
) : RemoteDataSource {

    override fun getSectionProducts(): Flow<PagingData<SectionProductData>> {
        return Pager(PagingConfig(pageSize = 20)) {
            SectionPagingSource(service = service)
        }.flow
    }
}