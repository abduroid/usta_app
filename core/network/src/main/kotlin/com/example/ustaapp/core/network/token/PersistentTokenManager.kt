package com.example.ustaapp.core.network.token

import android.util.Log
import androidx.datastore.core.DataStore
import com.example.ustaapp.core.datastore.Tokenpair
import com.example.ustaapp.core.datastore.copy
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersistentTokenManager @Inject constructor(
    private val tokenDataStore: DataStore<Tokenpair>,
) : TokenManager {
    override suspend fun saveAccessToken(token: String) {
        try {
            tokenDataStore.updateData {
                it.copy {
                    accessToken = token
                }
            }
        } catch (ioException: IOException) {
            Log.e("TokenManager", "Failed to update access token", ioException)
        }
    }

    override suspend fun saveRefreshToken(token: String) {
        try {
            tokenDataStore.updateData {
                it.copy {
                    refreshToken = token
                }
            }
        } catch (ioException: IOException) {
            Log.e("TokenManager", "Failed to update refresh token", ioException)
        }
    }

    // TODO Think about adding default value with ?:
    override suspend fun getAccessToken(): String? =
        tokenDataStore.data.map { it.accessToken }.firstOrNull()

    // TODO Think about adding default value with ?:
    override suspend fun getRefreshToken(): String? =
        tokenDataStore.data.map { it.refreshToken }.firstOrNull()

    override suspend fun clearAllTokens() {
        try {
            tokenDataStore.updateData {
                it.copy {
                    accessToken = ""
                    refreshToken = ""
                }
            }
        } catch (ioException: IOException) {
            Log.e("TokenManager", "Failed to clear tokens", ioException)
        }
    }
}
