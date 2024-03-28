package com.example.ustaapp.core.network.fake

import JvmUnitTestFakeAssetManager
import com.example.ustaapp.core.network.Dispatcher
import com.example.ustaapp.core.network.UstaDispatchers
import com.example.ustaapp.core.network.UstaNetworkDataSource
import com.example.ustaapp.core.network.model.NetworkAppointment
import com.example.ustaapp.core.network.model.NetworkTokenPair
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.threeten.bp.LocalDate
import javax.inject.Inject

class FakeUstaNetworkDataSource @Inject constructor(
    @Dispatcher(UstaDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: FakeAssetManager = JvmUnitTestFakeAssetManager,
) : UstaNetworkDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getSchedule(from: LocalDate, to: LocalDate): List<NetworkAppointment> =
        withContext(ioDispatcher) {
            assets.open("appointments.json").use(networkJson::decodeFromStream)
        }

    override suspend fun requestVerificationCode(phoneNumber: String) {
        TODO("Not yet implemented")
    }

    override suspend fun confirmVerificationCode(phoneNumber: String, code: String): NetworkTokenPair {
        TODO("Not yet implemented")
    }

    override suspend fun refreshToken(): NetworkTokenPair {
        TODO("Not yet implemented")
    }
}
