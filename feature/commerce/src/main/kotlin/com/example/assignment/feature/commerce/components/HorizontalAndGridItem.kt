package com.example.assignment.feature.commerce.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignment.feature.commerce.model.SectionProductModel

@Composable
internal fun HorizontalAndGridItem(
    product: SectionProductModel.Product,
    onLikedUpdate: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .padding(4.dp)
    ) {
        ProductImage(
            modifier = Modifier
                .size(width = 150.dp, height = 200.dp)
                .clip(RoundedCornerShape(8.dp)),
            productId = product.id,
            productImage = product.image,
            isLiked = product.isLiked,
            onLikedUpdate = onLikedUpdate
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = product.name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 2.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        PriceText(
            originalPrice = product.originalPrice,
            discountedPrice = product.discountedPrice
        )
    }
}

@Preview(
    name = "할인 있는 상태", showBackground = true, widthDp = 160
)
@Composable
private fun DiscountedPricePreview() {
    val sampleProduct = SectionProductModel.Product(
        id = 5067331,
        name = "[KF365] 삼겹살 대패 1kg (냉동)",
        image = "https://img-cf.kurly.com/shop/data/goods/1653040211228l0.jpg",
        originalPrice = 11900,
        discountedPrice = 8400,
        isSoldOut = false,
        isLiked = true
    )

    HorizontalAndGridItem(
        product = sampleProduct,
        onLikedUpdate = { }
    )
}

@Preview(
    name = "할인 없는 상태", showBackground = true, widthDp = 160
)
@Composable
private fun NoDiscountedPricePreview() {
    val sampleProduct = SectionProductModel.Product(
        id = 5067331,
        name = "[KF365] 삼겹살 대패 1kg (냉동)",
        image = "https://img-cf.kurly.com/shop/data/goods/1653040211228l0.jpg",
        originalPrice = 11900,
        isSoldOut = false,
        isLiked = true
    )

    HorizontalAndGridItem(
        product = sampleProduct,
        onLikedUpdate = { }
    )
}