package com.example.assignment.server.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductListResponse(
    @SerialName("data")
    val products: List<Product>
) {
    @Serializable
    data class Product(
        @SerialName("id")
        val id: Long,
        @SerialName("name")
        val name: String,
        @SerialName("image")
        val image: String,
        @SerialName("originalPrice")
        val originalPrice: Int,
        @SerialName("discountedPrice")
        val discountedPrice: Int? = null,
        @SerialName("isSoldOut")
        val isSoldOut: Boolean
    )

}