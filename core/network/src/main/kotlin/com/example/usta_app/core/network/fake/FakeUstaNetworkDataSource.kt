package com.example.usta_app.core.network.fake

import com.example.usta_app.core.network.Dispatcher
import JvmUnitTestFakeAssetManager
import com.example.usta_app.core.network.UstaNetworkDataSource
import com.example.usta_app.core.network.UstaDispatchers
import com.example.usta_app.core.network.model.NetworkAppointment
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

}
