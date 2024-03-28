package com.example.ustaapp.core.network.apitest

import JvmUnitTestFakeAssetManager
import com.example.ustaapp.core.network.fake.FakeAssetManager
import com.example.ustaapp.core.network.model.NetworkTokenPair
import com.example.ustaapp.core.network.retrofit.api.RefreshTokenApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import kotlin.test.assertEquals
import kotlin.test.assertFails

class RefreshTokenApiTest {

    private lateinit var server: MockWebServer
    private lateinit var api: RefreshTokenApi
    private lateinit var networkJson: Json
    private lateinit var assetsManager: FakeAssetManager

    private lateinit var expectedSuccessResponse: NetworkTokenPair
    private lateinit var expectedExpiredResponse: NetworkTokenPair

    @Before
    fun setup() {
        server = MockWebServer()
        networkJson = Json { ignoreUnknownKeys = true }
        assetsManager = JvmUnitTestFakeAssetManager
        api = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(RefreshTokenApi::class.java)

        expectedSuccessResponse = NetworkTokenPair(
            accessToken = "eyAccessToken",
            refreshToken = "eyRefreshToken",
        )
        expectedExpiredResponse = NetworkTokenPair(
            accessToken = null,
            refreshToken = null,
        )
    }

    @Test
    fun `Refresh token is still valid, gets new token pair`() {
        val mockResponse = MockResponse()
        assetsManager.open("successful_token_refresh_response.json").bufferedReader().use {
            mockResponse.setBody(it.readText())
        }
        server.enqueue(mockResponse)

        runBlocking {
            val actualResponse = api.refreshToken().data
            server.takeRequest()
            assertEquals(actual = actualResponse, expected = expectedSuccessResponse)
        }
    }

    @Test
    fun `Refresh token is expired, backend refuses to provide new token pair`() {
        val mockResponse = MockResponse()
        assetsManager.open("expired_token_refresh_response.json").bufferedReader().use {
            mockResponse.setBody(it.readText())
        }
        server.enqueue(mockResponse)
        runBlocking {
            assertFails {
                api.refreshToken()
                server.takeRequest()
            }
            // TODO after making data field of NetworkResponse nullable rewrite this part
//            assertEquals(actual = actualResponse, expected = expectedExpiredResponse)
        }
    }

    @Test
    fun `Refresh token is invalid, backend refuses to provide new token pair`() {
        val mockResponse = MockResponse()
        assetsManager.open("invalid_token_refresh_response.json").bufferedReader().use {
            mockResponse.setBody(it.readText())
        }
        server.enqueue(mockResponse)
        runBlocking {
            assertFails {
                api.refreshToken()
                server.takeRequest()
            }
            // TODO after making data field of NetworkResponse nullable, rewrite this part
            // so it checks message from backend with expected message to know exact failure.
        }
    }
}
