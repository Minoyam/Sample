package com.example.assignment.feature.commerce.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.assignment.feature.commerce.R

@Composable
internal fun ProductImage(
    modifier: Modifier = Modifier,
    productId: Long,
    productImage: String,
    isLiked: Boolean,
    onLikedUpdate: (Long) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = productImage,
            contentDescription = "상품 이미지",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        IconButton(
            onClick = { onLikedUpdate(productId) },
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            Icon(
                painter = painterResource(
                    id = if (isLiked) R.drawable.ic_btn_heart_on
                    else R.drawable.ic_btn_heart_off
                ),
                contentDescription = "찜하기",
                tint = Color.Unspecified
            )
        }
    }
}

@Preview(name = "찜 상태", showBackground = true)
@Composable
private fun LikedPreview() {
    ProductImage(
        productId = 5067331,
        productImage = "https://img-cf.kurly.com/shop/data/goods/1653040211228l0.jpg",
        isLiked = true,
        onLikedUpdate = {}
    )
}

@Preview(name = "찜 해제 상태", showBackground = true)
@Composable
private fun UnlikedPreview() {
    ProductImage(
        productId = 5067331,
        productImage = "https://img-cf.kurly.com/shop/data/goods/1653040211228l0.jpg",
        isLiked = false,
        onLikedUpdate = {}
    )
}