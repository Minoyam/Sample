package com.example.assignment.server

import android.os.SystemClock
import com.example.assignment.server.core.AssetFileProvider
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class MockInterceptor @Inject constructor(assetFileProvider: AssetFileProvider) : Interceptor {

    private val mockServer = MockServer(assetFileProvider)

    override fun intercept(chain: Interceptor.Chain): Response {
        SystemClock.sleep(Random.nextInt(1, 3) * 1000L)

        val responseString = mockServer.get(chain.request())

        return chain.proceed(chain.request())
            .newBuilder()
            .apply {
                if (responseString == null) {
                    code(500)
                    message("에러 발생")
                } else {
                    message(responseString)
                    body(
                        responseString.toByteArray()
                            .toResponseBody("application/json".toMediaType())
                    )
                    protocol(Protocol.HTTP_2)
                    addHeader("content-type", "application/json")
                    code(200)
                }
            }
            .build()
    }
}
