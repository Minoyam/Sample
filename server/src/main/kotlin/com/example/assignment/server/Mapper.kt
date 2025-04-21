package com.example.assignment.server

import com.example.assignment.data.model.SectionProductData
import com.example.assignment.server.response.ProductListResponse
import java.util.Locale

fun String.toSectionType() =
    when (lowercase(Locale.getDefault())) {
        "grid" -> SectionProductData.SectionType.GRID
        "horizontal" -> SectionProductData.SectionType.HORIZONTAL
        "vertical" -> SectionProductData.SectionType.VERTICAL
        else -> error("Error : Convert SectionType")
    }

fun ProductListResponse.Product.toData() = SectionProductData.Product(
    id = id,
    name = name,
    image = image,
    originalPrice = originalPrice,
    discountedPrice = discountedPrice,
    isSoldOut = isSoldOut
)