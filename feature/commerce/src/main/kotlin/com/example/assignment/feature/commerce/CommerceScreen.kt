package com.example.assignment.feature.commerce

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.assignment.feature.commerce.components.HorizontalAndGridItem
import com.example.assignment.feature.commerce.components.LoadingIndicator
import com.example.assignment.feature.commerce.components.VerticalItem
import com.example.assignment.feature.commerce.model.SectionProductModel
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CommerceScreen(
    sectionProductModel: LazyPagingItems<SectionProductModel>,
    onLikedUpdate: (Long) -> Unit,
    onError: (Throwable) -> Unit,
) {
    when (val loadState = sectionProductModel.loadState.refresh) {
        is LoadState.Error -> {
            onError(loadState.error)
        }

        LoadState.Loading -> {
            LoadingIndicator()
        }

        else -> {
            PullToRefreshBox(
                isRefreshing = loadState is LoadState.Loading,
                onRefresh = { sectionProductModel.refresh() }
            ) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(
                        count = sectionProductModel.itemCount,
                        key = { index -> sectionProductModel[index]?.sectionTitle ?: index },
                    ) { index ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                        )
                        {
                            sectionProductModel[index]?.let { item ->
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = item.sectionTitle,
                                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 16.sp)
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                when (item.sectionType) {
                                    SectionProductModel.SectionType.GRID -> {
                                        GridSection(
                                            sectionProductModel = item,
                                            onLikedUpdate = onLikedUpdate
                                        )
                                    }

                                    SectionProductModel.SectionType.HORIZONTAL -> {
                                        HorizontalSection(
                                            sectionProductModel = item,
                                            onLikedUpdate = onLikedUpdate
                                        )
                                    }

                                    SectionProductModel.SectionType.VERTICAL -> {
                                        VerticalSection(
                                            sectionProductModel = item,
                                            onLikedUpdate = onLikedUpdate
                                        )
                                    }
                                }
                            }
                        }
                        if (index != sectionProductModel.itemCount - 1) {
                            Spacer(modifier = Modifier.height(16.dp))
                            HorizontalDivider(
                                color = Color.LightGray,
                                thickness = 1.dp,
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                    if (sectionProductModel.loadState.append is LoadState.Loading) {
                        item { LoadingIndicator() }
                    }
                }
            }
        }
    }
}

@Composable
private fun VerticalSection(
    sectionProductModel: SectionProductModel,
    onLikedUpdate: (Long) -> Unit,
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        sectionProductModel.products.forEach { product ->
            VerticalItem(
                product = product,
                onLikedUpdate = onLikedUpdate
            )
        }
    }
}

@Composable
private fun HorizontalSection(
    sectionProductModel: SectionProductModel,
    onLikedUpdate: (Long) -> Unit,
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(
            items = sectionProductModel.products,
            key = { product -> product.id }
        ) { product ->
            HorizontalAndGridItem(
                product = product,
                onLikedUpdate = onLikedUpdate
            )
        }
    }
}

@Composable
private fun GridSection(
    sectionProductModel: SectionProductModel,
    onLikedUpdate: (Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 700.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = false
    ) {
        items(
            items = sectionProductModel.products.take(6),
            key = { product -> product.id }
        ) { product ->
            HorizontalAndGridItem(
                product = product,
                onLikedUpdate = onLikedUpdate
            )
        }
    }
}

@Composable
@Preview(name = "상품 리스트", showBackground = true)
private fun CommerceScreenPreview() {
    val fakeSections = listOf(
        SectionProductModel(
            sectionTitle = "함께하면 더 좋은 상품",
            sectionType = SectionProductModel.SectionType.GRID,
            products = List(6) { idx ->
                SectionProductModel.Product(
                    id = idx.toLong(),
                    name = "상품 $idx",
                    image = "", originalPrice = 1000 + idx * 100,
                    discountedPrice = if (idx % 2 == 0) 900 else null,
                    isSoldOut = false,
                    isLiked = idx % 3 == 0
                )
            }
        ),
        SectionProductModel(
            sectionTitle = "오늘 하루 특가 세일",
            sectionType = SectionProductModel.SectionType.HORIZONTAL,
            products = List(5) { idx ->
                SectionProductModel.Product(
                    id = (100 + idx).toLong(),
                    name = "특가 상품 $idx",
                    image = "", originalPrice = 2000,
                    discountedPrice = 1500,
                    isSoldOut = false,
                    isLiked = false
                )
            }
        ),
        SectionProductModel(
            sectionTitle = "놓치면 안될 상품",
            sectionType = SectionProductModel.SectionType.VERTICAL,
            products = List(4) { idx ->
                SectionProductModel.Product(
                    id = (200 + idx).toLong(),
                    name = "세로 상품 $idx",
                    image = "", originalPrice = 3000,
                    discountedPrice = null,
                    isSoldOut = false,
                    isLiked = idx == 1
                )
            }
        )
    )
    val fakePagingItems = MutableStateFlow(PagingData.from(fakeSections)).collectAsLazyPagingItems()

    CommerceScreen(
        sectionProductModel = fakePagingItems,
        onLikedUpdate = {},
        onError = {}
    )
}
