package com.example.kallapp.core.network.fake

import com.example.kallapp.core.network.Dispatcher
import com.example.kallapp.core.network.KallaDispatchers
import com.example.kallapp.core.network.KallaNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import javax.inject.Inject

class FakeKallaNetworkDataSource @Inject constructor(
    @Dispatcher(KallaDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
) : KallaNetworkDataSource {

    override suspend fun getSchedule(): List<Int> {
        // TODO implement
        return emptyList()
    }
}
