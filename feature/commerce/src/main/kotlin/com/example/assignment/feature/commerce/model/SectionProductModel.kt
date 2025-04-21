package com.example.assignment.feature.commerce.model

internal data class SectionProductModel(
    val sectionTitle: String,
    val sectionType: SectionType,
    val products: List<Product>
) {
    internal data class Product(
        val id: Long,
        val name: String,
        val image: String,
        val originalPrice: Int,
        val discountedPrice: Int? = null,
        val isSoldOut: Boolean,
        val isLiked: Boolean = false
    )

    internal enum class SectionType {
        GRID, HORIZONTAL, VERTICAL
    }
}