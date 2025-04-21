package com.example.assignment.data.source

import androidx.paging.PagingData
import com.example.assignment.data.model.SectionProductData
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getSectionProducts(): Flow<PagingData<SectionProductData>>

}