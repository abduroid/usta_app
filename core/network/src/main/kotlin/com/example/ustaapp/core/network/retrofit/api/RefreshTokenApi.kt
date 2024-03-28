package com.example.ustaapp.core.network.retrofit.api

import com.example.ustaapp.core.network.model.NetworkTokenPair
import com.example.ustaapp.core.network.retrofit.NetworkResponse
import retrofit2.http.POST

interface RefreshTokenApi {

    @POST(value = "renew_token")
    suspend fun refreshToken(): NetworkResponse<NetworkTokenPair>
}
