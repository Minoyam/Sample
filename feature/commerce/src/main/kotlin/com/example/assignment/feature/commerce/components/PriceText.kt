package com.example.assignment.feature.commerce.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.assignment.core.designsystem.Orange
import com.example.assignment.feature.commerce.calculateDiscountPercent

@Composable
internal fun PriceText(originalPrice: Int, discountedPrice: Int?) {
    if (discountedPrice != null) {
        val discountPercent =
            calculateDiscountPercent(originalPrice, discountedPrice)

        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(color = Orange, fontWeight = FontWeight.Bold)) {
                    append("${discountPercent}% ")
                }
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("${discountedPrice}원 ")
                }
                withStyle(
                    SpanStyle(
                        color = Color.Gray,
                        textDecoration = TextDecoration.LineThrough
                    )
                ) {
                    append("${originalPrice}원")
                }
            },
            style = MaterialTheme.typography.bodySmall
        )
    } else {
        Text(
            text = "${originalPrice}원",
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Preview(name = "할인 있는 상태", showBackground = true)
@Composable
private fun DiscountedPricePreview() {
    PriceText(
        originalPrice = 11900,
        discountedPrice = 8400,
    )
}

@Preview(name = "할인 없는 상태", showBackground = true)
@Composable
private fun NoDiscountedPricePreview() {
    PriceText(
        originalPrice = 11900,
        discountedPrice = null,
    )
}