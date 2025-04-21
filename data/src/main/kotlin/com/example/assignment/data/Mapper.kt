package com.example.assignment.data

import androidx.paging.PagingData
import androidx.paging.map
import com.example.assignment.data.model.SectionProductData
import com.example.assignment.domain.model.SectionProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal fun Flow<PagingData<SectionProductData>>.toDomain(): Flow<PagingData<SectionProduct>> =
    map { pagingData ->
        pagingData.map { sectionProductsData ->
            sectionProductsData.toDomain()
        }
    }

private fun SectionProductData.toDomain() = SectionProduct(
    sectionTitle = sectionTitle,
    sectionType = sectionType.toDomain(),
    products = products.map { product -> product.toDomain() }
)

private fun SectionProductData.Product.toDomain() = SectionProduct.Product(
    id = id,
    name = name,
    image = image,
    originalPrice = originalPrice,
    discountedPrice = discountedPrice,
    isSoldOut = isSoldOut
)

private fun SectionProductData.SectionType.toDomain() = when (this) {
    SectionProductData.SectionType.GRID -> SectionProduct.SectionType.GRID
    SectionProductData.SectionType.HORIZONTAL -> SectionProduct.SectionType.HORIZONTAL
    SectionProductData.SectionType.VERTICAL -> SectionProduct.SectionType.VERTICAL
}