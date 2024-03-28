package com.example.ustaapp.core.network.retrofit.api

import com.example.ustaapp.core.network.model.NetworkTokenPair
import com.example.ustaapp.core.network.retrofit.NetworkResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @POST(value = "auth/request_verification_code")
    suspend fun requestVerificationCode(
        @Query("phone_number") phoneNumber: String,
    ): NetworkResponse<String>

    @FormUrlEncoded
    @POST(value = "confirm_verification_code")
    suspend fun confirmVerificationCode(
        @Field("totp") code: String,
    ): NetworkResponse<NetworkTokenPair>
}
