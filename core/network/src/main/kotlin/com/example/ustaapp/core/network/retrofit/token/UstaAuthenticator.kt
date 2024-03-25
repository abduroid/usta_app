package com.example.ustaapp.core.network.retrofit.token

import com.example.ustaapp.core.network.UstaNetworkDataSource
import javax.inject.Inject

class UstaAuthenticator @Inject constructor(
    private val ustaNetworkDataSource: UstaNetworkDataSource,
)
//    : Authenticator {
//    override fun authenticate(route: Route?, response: Response): Request? {
//    }
//
//    private suspend fun refreshToken() {
//    }
// }
