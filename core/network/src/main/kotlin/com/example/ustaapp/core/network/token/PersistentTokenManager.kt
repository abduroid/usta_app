package com.example.ustaapp.core.network.token

import android.util.Log
import androidx.datastore.core.DataStore
import com.example.ustaapp.core.datastore.Tokenpair
import com.example.ustaapp.core.datastore.copy
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import org.threeten.bp.LocalDateTime
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersistentTokenManager @Inject constructor(
    private val tokenDataStore: DataStore<Tokenpair>,
) : TokenManager {

    override suspend fun saveAccessToken(token: String, expiresAt: LocalDateTime) {
        try {
            tokenDataStore.updateData {
                it.copy {
                    accessToken = token
                    accessTokenExpiresAt = expiresAt.toString() // TODO add serializer
                }
            }
        } catch (ioException: IOException) {
            Log.e("TokenManager", "Failed to update access token", ioException)
        }
    }

    override suspend fun saveRefreshToken(token: String, expiresAt: LocalDateTime) {
        try {
            tokenDataStore.updateData {
                it.copy {
                    refreshToken = token
                    refreshTokenExpiresAt = expiresAt.toString() // TODO add serializer
                }
            }
        } catch (ioException: IOException) {
            Log.e("TokenManager", "Failed to update refresh token", ioException)
        }
    }

    override suspend fun getAccessToken(): String? =
        tokenDataStore.data.map { it.accessToken }.firstOrNull()

    override suspend fun getRefreshToken(): String? =
        tokenDataStore.data.map { it.refreshToken }.firstOrNull()

    override suspend fun getAccessTokenExpiresAt(): LocalDateTime? =
        LocalDateTime.parse(tokenDataStore.data.map { it.accessTokenExpiresAt }.firstOrNull())

    override suspend fun getRefreshTokenExpiresAt(): LocalDateTime? =
        LocalDateTime.parse(tokenDataStore.data.map { it.refreshTokenExpiresAt }.firstOrNull())

    override suspend fun clearAllTokens() {
        try {
            tokenDataStore.updateData {
                it.copy {
                    accessToken = ""
                    accessTokenExpiresAt = ""
                    refreshToken = ""
                    refreshTokenExpiresAt = ""
                }
            }
        } catch (ioException: IOException) {
            Log.e("TokenManager", "Failed to clear tokens", ioException)
        }
    }
}
