package com.example.assignment.server

import com.example.assignment.server.core.FileProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class MockServerTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("TestAssetFileProvider")
    lateinit var testAssetFileProvider: FileProvider

    @Inject
    @Named("AssetFileProvider")
    lateinit var assetFileProvider: FileProvider

    @Inject
    internal lateinit var service: Service

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun `리소스_파일_읽기_테스트`() {
        println(testAssetFileProvider.getJsonFromAsset("file_read_test.json"))
    }

    @Test
    fun `sections_최대_page가_넘어가는_요청_시_500_error`() {
        var index = 1
        runBlocking {
            while (true) {
                runCatching {
                    service.getSections(page = index)
                }.onSuccess {
                    index++
                }.onFailure {
                    assertEquals("HTTP 500 에러 발생", it.message)
                    println("page : $index")
                    return@runBlocking
                }
            }
        }
    }
    @Test
    fun `sections_200_응답_확인`() {
        runBlocking {
            println(service.getSections(page = 1).toString())
        }
    }


    @Test
    fun `products_잘못된_sectionId_요청_시_500_error`() {
        runBlocking {
            runCatching {
                service.getProducts(sectionId = 99999999)
            }.onFailure {
                assertEquals("HTTP 500 에러 발생", it.message)
                return@runBlocking
            }
        }
    }

    @Test
    fun `products_200_응답_확인`() {
        runBlocking {
            println(service.getProducts(sectionId = 1).toString())
        }
    }
}
