package com.example.ustaapp.core.network.fake

import JvmUnitTestFakeAssetManager
import com.example.ustaapp.core.network.model.NetworkAppointment
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import kotlin.test.assertEquals

class FakeUstaNetworkDataSourceTest {

    private lateinit var subject: FakeUstaNetworkDataSource

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        subject = FakeUstaNetworkDataSource(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true },
            assets = JvmUnitTestFakeAssetManager,
        )
    }

    @Suppress("ktlint:standard:max-line-length")
    @Test
    fun testDeserializationOfAppointments() = runTest(testDispatcher) {
        val mockDate = LocalDate.of(2024, 3, 11)
        val mockDateTime = LocalDateTime.of(2024, 3, 11, 17, 20, 13)
        assertEquals(
            NetworkAppointment(
                id = "32d7e605-4faf-426d-bdcf-4288e83b5128",
                barberId = "db048865-9a7f-4c0c-99e1-9c2d6a773381",
                clientId = "b10270bc-65ca-415a-ada2-a1772cf28ab0",
                clientFirstName = "Abdulaziz",
                clientLastName = "Abduqodirov",
                selectedDate = mockDate,
                startTime = 17.0f,
                endTime = 17.5f,
                createdDateTime = mockDateTime,
            ),
            subject.getSchedule(mockDate, mockDate).first(),
        )
    }
}
