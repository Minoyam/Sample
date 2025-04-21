package com.example.assignment.server.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SectionResponse(
    @SerialName("data")
    val sectionItems: List<SectionItem>,
    @SerialName("paging")
    val paging: Paging? = null
) {
    @Serializable
    data class SectionItem(
        @SerialName("title")
        val title: String,
        @SerialName("id")
        val id: Int,
        @SerialName("type")
        val type: String,
        @SerialName("url")
        val url: String
    )

    @Serializable
    data class Paging(
        @SerialName("next_page")
        val nextPage: Int
    )
}