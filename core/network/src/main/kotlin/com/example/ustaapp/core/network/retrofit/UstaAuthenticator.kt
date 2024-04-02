package com.example.ustaapp.core.network.retrofit

import com.example.ustaapp.core.network.retrofit.api.RefreshTokenApi
import com.example.ustaapp.core.network.token.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class UstaAuthenticator @Inject constructor(
    @TokenRefresherClient
    private val refreshTokenApi: RefreshTokenApi,
    private val tokenManager: TokenManager,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        // If accessToken isn't there or has refresh token already
        if (!response.hasToken("Authorization") || response.hasToken("X-Refresh-Token")) return null

        synchronized(this) {
            val newTokenPair = runBlocking { refreshTokenApi.refreshToken().data }
            with(newTokenPair) {
                runBlocking {
                    tokenManager.saveAccessToken(accessToken)
                    tokenManager.saveRefreshToken(refreshToken)
                }
            }
            return response.request.newBuilder()
                .addHeader("Authorization", "Bearer ${newTokenPair.accessToken}")
                .build()
        }
    }

    private fun Response.hasToken(header: String): Boolean {
        val accessToken = request.headers[header]?.removePrefix("Bearer")?.trim()
        return !accessToken.isNullOrBlank()
    }
}
