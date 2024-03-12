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
import retrofit2.http.GET
import javax.inject.Inject

private const val USTA_BASE_URL = com.example.ustaapp.core.network.BuildConfig.BACKEND_URL
private interface RetrofitUstaNetworkApi {

    @GET(value = "schedule")
    suspend fun getSchedule(): NetworkResponse<List<Int>>
}

@Serializable
private data class NetworkResponse<T>(
    val data: T,
)

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
        return emptyList()
    }
}
