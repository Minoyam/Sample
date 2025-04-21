package com.example.assignment.feature.commerce

import com.example.assignment.domain.model.SectionProduct
import com.example.assignment.feature.commerce.model.SectionProductModel

internal fun SectionProduct.toModel(likedIds: Set<Long>) = SectionProductModel(
    sectionTitle = sectionTitle,
    sectionType = sectionType.toModel(),
    products = products.map { product -> product.toModel(likedIds = likedIds) }
)

private fun SectionProduct.Product.toModel(likedIds: Set<Long>) = SectionProductModel.Product(
    id = id,
    name = name,
    image = image,
    originalPrice = originalPrice,
    discountedPrice = discountedPrice,
    isSoldOut = isSoldOut,
    isLiked = likedIds.contains(id)
)

private fun SectionProduct.SectionType.toModel() = when (this) {
    SectionProduct.SectionType.GRID -> SectionProductModel.SectionType.GRID
    SectionProduct.SectionType.HORIZONTAL -> SectionProductModel.SectionType.HORIZONTAL
    SectionProduct.SectionType.VERTICAL -> SectionProductModel.SectionType.VERTICAL
}