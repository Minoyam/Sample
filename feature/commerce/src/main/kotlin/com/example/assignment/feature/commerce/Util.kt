package com.example.assignment.feature.commerce

import kotlin.math.roundToInt

internal fun calculateDiscountPercent(
    originalPrice: Int,
    discountedPrice: Int?
): Int {
    if (originalPrice <= 0 || discountedPrice == null || discountedPrice >= originalPrice) return 0
    val discount = ((originalPrice - discountedPrice).toDouble() / originalPrice) * 100
    return discount.roundToInt()
}