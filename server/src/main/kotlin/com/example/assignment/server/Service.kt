package com.example.assignment.server

import com.example.assignment.server.response.ProductListResponse
import com.example.assignment.server.response.SectionResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface Service {

    @GET("/sections")
    suspend fun getSections(
        @Query("page") page: Int
    ): SectionResponse

    @GET("/section/products")
    suspend fun getProducts(
        @Query("sectionId") sectionId: Int,
    ): ProductListResponse

    companion object {
        internal const val BASE_URL = "https://kurly.com/"
    }
}