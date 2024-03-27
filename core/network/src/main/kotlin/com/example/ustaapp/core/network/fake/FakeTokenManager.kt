package com.example.ustaapp.core.network.fake

import com.example.ustaapp.core.network.token.TokenManager

class FakeTokenManager : TokenManager {

    private var accessToken: String = ""
    private var refreshToken: String = ""

    override suspend fun saveAccessToken(token: String) {
        accessToken = token
    }

    override suspend fun saveRefreshToken(token: String) {
        refreshToken = token
    }

    override suspend fun getAccessToken(): String = accessToken

    override suspend fun getRefreshToken(): String = refreshToken

    override suspend fun clearAllTokens() {
        accessToken = ""
        refreshToken = ""
    }
}
