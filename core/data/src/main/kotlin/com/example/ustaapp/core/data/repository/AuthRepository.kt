package com.example.ustaapp.core.data.repository

import com.example.ustaapp.core.network.Dispatcher
import com.example.ustaapp.core.network.UstaDispatchers.IO
import com.example.ustaapp.core.network.UstaNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val ustaNetworkDataSource: UstaNetworkDataSource,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) {
    suspend fun requestVerificationCode(phoneNumber: String) =
        withContext(ioDispatcher) {
            ustaNetworkDataSource.requestVerificationCode(phoneNumber)
        }

    suspend fun confirmVerificationCode(phoneNumber: String, code: String) =
        withContext(ioDispatcher) {
            ustaNetworkDataSource.confirmVerificationCode(phoneNumber, code)
        }
}
