package com.example.ustaapp.core.network.fake

import com.example.ustaapp.core.network.token.TokenManager
import org.threeten.bp.LocalDateTime

class FakeTokenManager : TokenManager {

    private var accessToken: String = ""
    private var accessTokenExpiresAt: String = ""
    private var refreshToken: String = ""
    private var refreshTokenExpiresAt: String = ""

    override suspend fun saveAccessToken(token: String, expiresAt: LocalDateTime) {
        accessToken = token
        accessTokenExpiresAt = expiresAt.toString()
    }

    override suspend fun saveRefreshToken(token: String, expiresAt: LocalDateTime) {
        refreshToken = token
        refreshTokenExpiresAt = expiresAt.toString()
    }

    override suspend fun getAccessToken(): String = accessToken

    override suspend fun getAccessTokenExpiresAt(): LocalDateTime? =
        LocalDateTime.parse(accessTokenExpiresAt)

    override suspend fun getRefreshToken(): String = refreshToken

    override suspend fun getRefreshTokenExpiresAt(): LocalDateTime? =
        LocalDateTime.parse(refreshTokenExpiresAt)

    override suspend fun clearAllTokens() {
        accessToken = ""
        refreshToken = ""
    }
}
