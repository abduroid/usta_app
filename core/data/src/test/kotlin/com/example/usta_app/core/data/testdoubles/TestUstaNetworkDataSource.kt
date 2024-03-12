package com.example.usta_app.core.data.testdoubles

import com.example.usta_app.core.network.UstaNetworkDataSource
import com.example.usta_app.core.network.fake.FakeUstaNetworkDataSource
import com.example.usta_app.core.network.model.NetworkAppointment
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.serialization.json.Json
import org.threeten.bp.LocalDate

class TestUstaNetworkDataSource : UstaNetworkDataSource {

    private val source = FakeUstaNetworkDataSource(
        UnconfinedTestDispatcher(),
        Json { ignoreUnknownKeys = true },
    )

    override suspend fun getSchedule(from: LocalDate, to: LocalDate): List<NetworkAppointment> =
        source.getSchedule(from, to)
}