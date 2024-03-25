package com.example.ustaapp.core.network.retrofit

import com.example.ustaapp.core.network.BuildConfig
import com.example.ustaapp.core.network.UstaNetworkDataSource
import com.example.ustaapp.core.network.model.NetworkAppointment
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import org.threeten.bp.LocalDate
import retrofit2.Retrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private const val USTA_BASE_URL = BuildConfig.BACKEND_URL
internal interface RetrofitUstaNetworkApi {

    @GET(value = "schedule")
    suspend fun getSchedule(): NetworkResponse<List<NetworkAppointment>>

    @POST(value = "request_verification_code")
    suspend fun requestVerificationCode(
        @Query("phone_number") phoneNumber: String,
    ): NetworkResponse<Unit>

    @FormUrlEncoded
    @POST(value = "confirm_verification_code")
    suspend fun confirmVerificationCode(
        @Field("totp") code: String,
    ): Map<String, String>
}

@Serializable
data class NetworkResponse<T>(
    // TODO #1 make data field nullable and emit as error when its value null
    val data: T,
    val message: String? = null,
)

@Singleton
internal class RetrofitUstaNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : UstaNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(USTA_BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitUstaNetworkApi::class.java)

    override suspend fun getSchedule(from: LocalDate, to: LocalDate): List<NetworkAppointment> {
        return networkApi.getSchedule().data
    }

    override suspend fun requestVerificationCode(phoneNumber: String) {
        networkApi.requestVerificationCode(phoneNumber)
    }

    override suspend fun confirmVerificationCode(code: String) {
        networkApi.confirmVerificationCode(code)
    }
}
