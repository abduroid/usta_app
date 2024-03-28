package com.example.ustaapp.core.network.retrofit.api

import com.example.ustaapp.core.network.model.NetworkAppointment
import com.example.ustaapp.core.network.retrofit.NetworkResponse
import retrofit2.http.GET

interface UstaApi {

    @GET(value = "schedule/own-schedule")
    suspend fun getSchedule(): NetworkResponse<List<NetworkAppointment>>
}
