package com.example.ustaapp.core.network.token

/**
 * Manager for token logic
 */
interface TokenManager {
    suspend fun saveAccessToken(token: String)

    suspend fun saveRefreshToken(token: String)

    suspend fun getAccessToken(): String?

    suspend fun getRefreshToken(): String?

    suspend fun clearAllTokens()
}
