package com.example.ustaapp.core.network.retrofit

import com.example.ustaapp.core.network.UstaNetworkDataSource
import com.example.ustaapp.core.network.token.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class UstaAuthenticator @Inject constructor(
    private val ustaNetworkDataSource: UstaNetworkDataSource,
    private val tokenManager: TokenManager,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this) {
            val accessToken = runBlocking {
                tokenManager.getAccessToken()
            }
            val updatedAccessToken = runBlocking {
//                ustaNetworkDataSource.
            }
        }
        // TODO temporary
        return null
    }
}
