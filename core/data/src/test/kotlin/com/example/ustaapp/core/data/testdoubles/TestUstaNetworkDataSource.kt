package com.example.ustaapp.core.data.testdoubles

import com.example.ustaapp.core.network.UstaNetworkDataSource
import com.example.ustaapp.core.network.fake.FakeUstaNetworkDataSource
import com.example.ustaapp.core.network.model.NetworkAppointment
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
