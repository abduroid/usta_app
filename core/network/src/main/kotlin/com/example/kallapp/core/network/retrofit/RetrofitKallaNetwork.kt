package com.example.kallapp.core.network.retrofit

import com.example.kallapp.core.network.BuildConfig
import com.example.kallapp.core.network.KallaNetworkDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

private const val KALLA_BASE_URL = BuildConfig.BACKEND_URL
private interface RetrofitKallaNetworkApi {

    @GET(value = "schedule")
    suspend fun getSchedule(): NetworkResponse<List<Int>>
}

@Serializable
private data class NetworkResponse<T>(
    val data: T,
)

internal class RetrofitKallaNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : KallaNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(KALLA_BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitKallaNetworkApi::class.java)

    override suspend fun getSchedule(): List<Int> {
        return emptyList()
    }
}
