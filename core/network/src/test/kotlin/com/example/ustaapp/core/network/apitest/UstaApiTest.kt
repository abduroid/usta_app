package com.example.ustaapp.core.network.apitest

import JvmUnitTestFakeAssetManager
import com.example.ustaapp.core.network.fake.FakeAssetManager
import com.example.ustaapp.core.network.model.NetworkAppointment
import com.example.ustaapp.core.network.retrofit.RetrofitUstaNetworkApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import retrofit2.Retrofit
import retrofit2.create
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class UstaApiTest {

    private lateinit var server: MockWebServer
    private lateinit var api: RetrofitUstaNetworkApi
    private lateinit var networkJson: Json
    private lateinit var assetsManager: FakeAssetManager
    private lateinit var expectedResponse: NetworkAppointment

    @Before
    fun setup() {
        server = MockWebServer()
        networkJson = Json { ignoreUnknownKeys = true }
        assetsManager = JvmUnitTestFakeAssetManager
        api = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build().create()

        val mockDate = LocalDate.of(2024, 3, 11)
        val mockDateTime = LocalDateTime.of(2024, 3, 11, 17, 20, 13)
        expectedResponse = NetworkAppointment(
            id = "32d7e605-4faf-426d-bdcf-4288e83b5128",
            barberId = "db048865-9a7f-4c0c-99e1-9c2d6a773381",
            clientId = "b10270bc-65ca-415a-ada2-a1772cf28ab0",
            clientFirstName = "Abdulaziz",
            clientLastName = "Abduqodirov",
            selectedDate = mockDate,
            startTime = 17.0f,
            endTime = 17.5f,
            createdDateTime = mockDateTime,
        )
    }

    @Test
    fun `getAppointments returns Success with correct JSON`() = runTest {
        val res = MockResponse()
        assetsManager.open("appointments_wrapped.json").bufferedReader().use {
            res.setBody(it.readText())
        }
        server.enqueue(res)

        val response = api.getSchedule().data.first()
        server.takeRequest()
        assertEquals(actual = response, expected = expectedResponse)
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Test
    fun `getAppointments fails with failure JSON`() = runTest {
        val res = MockResponse()
        res.setBody(
            """
            {
                "message": "An error occurred"
            }
            """.trimIndent(),
        )
        server.enqueue(res)

        assertFailsWith(MissingFieldException::class) {
            api.getSchedule().message
            server.takeRequest()
        }
    }

    @After
    fun after() {
        server.shutdown()
    }
}
