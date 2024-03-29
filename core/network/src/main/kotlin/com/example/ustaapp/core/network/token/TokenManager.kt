package com.example.ustaapp.core.network.token

import org.threeten.bp.LocalDateTime

/**
 * Manager for token logic
 */
interface TokenManager {
    suspend fun saveAccessToken(token: String, expiresAt: LocalDateTime)
    suspend fun saveRefreshToken(token: String, expiresAt: LocalDateTime)
    suspend fun getAccessToken(): String?
    suspend fun getAccessTokenExpiresAt(): LocalDateTime?
    suspend fun getRefreshToken(): String?
    suspend fun getRefreshTokenExpiresAt(): LocalDateTime?

    suspend fun clearAllTokens()
}
