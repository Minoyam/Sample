package com.example.assignment.data.model

data class SectionProductData(
    val sectionTitle: String,
    val sectionType: SectionType,
    val products: List<Product>
) {
    data class Product(
        val id: Long,
        val name: String,
        val image: String,
        val originalPrice: Int,
        val discountedPrice: Int? = null,
        val isSoldOut: Boolean
    )

    enum class SectionType {
        GRID, HORIZONTAL, VERTICAL
    }
}