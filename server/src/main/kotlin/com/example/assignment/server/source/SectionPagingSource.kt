package com.example.assignment.server.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.assignment.data.model.SectionProductData
import com.example.assignment.server.Service
import com.example.assignment.server.toData
import com.example.assignment.server.toSectionType
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope

internal class SectionPagingSource(
    private val service: Service,
) : PagingSource<Int, SectionProductData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SectionProductData> {
        val page = params.key ?: 1

        return runCatching {
            val sectionResponse = service.getSections(page)

            val sectionWithProductsList = supervisorScope {
                sectionResponse.sectionItems.map { section ->
                    async {
                        val products = service.getProducts(section.id).products
                        SectionProductData(
                            sectionTitle = section.title,
                            sectionType = section.type.toSectionType(),
                            products = products.map { product -> product.toData() }
                        )
                    }
                }.awaitAll()
            }

            LoadResult.Page(
                data = sectionWithProductsList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = sectionResponse.paging?.nextPage
            )
        }.getOrElse { e ->
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SectionProductData>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}