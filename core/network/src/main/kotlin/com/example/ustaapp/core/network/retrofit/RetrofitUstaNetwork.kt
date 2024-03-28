package com.example.ustaapp.core.network.retrofit

import com.example.ustaapp.core.network.BuildConfig
import com.example.ustaapp.core.network.UstaNetworkDataSource
import com.example.ustaapp.core.network.model.NetworkAppointment
import com.example.ustaapp.core.network.model.NetworkTokenPair
import com.example.ustaapp.core.network.retrofit.api.AuthApi
import com.example.ustaapp.core.network.retrofit.api.RefreshTokenApi
import com.example.ustaapp.core.network.retrofit.api.UstaApi
import kotlinx.serialization.Serializable
import org.threeten.bp.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

private const val USTA_BASE_URL = BuildConfig.BACKEND_URL

@Serializable
data class NetworkResponse<T>(
    // TODO #1 make data field nullable and emit as error when its value null
    val data: T,
    val message: String? = null,
)

@Singleton
class RetrofitUstaNetwork @Inject constructor(
    @AuthenticatedClient private val ustaApi: UstaApi,
    @TokenRefresherClient private val tokenApi: RefreshTokenApi,
    @UnauthenticatedClient private val authApi: AuthApi,
) : UstaNetworkDataSource {

    override suspend fun getSchedule(from: LocalDate, to: LocalDate): List<NetworkAppointment> {
        return ustaApi.getSchedule().data
    }

    override suspend fun requestVerificationCode(phoneNumber: String) {
        authApi.requestVerificationCode(phoneNumber)
    }

    override suspend fun confirmVerificationCode(
        phoneNumber: String,
        code: String,
    ): NetworkTokenPair =
        authApi.confirmVerificationCode(
            phoneNumber = phoneNumber,
            code = code,
        ).data

    override suspend fun refreshToken(): NetworkTokenPair {
        return tokenApi.refreshToken().data
    }
}
