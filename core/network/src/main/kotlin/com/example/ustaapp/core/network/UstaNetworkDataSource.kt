package com.example.ustaapp.core.network

import com.example.ustaapp.core.network.model.NetworkAppointment
import org.threeten.bp.LocalDate

interface UstaNetworkDataSource {
    suspend fun getSchedule(from: LocalDate, to: LocalDate): List<NetworkAppointment>

    suspend fun requestVerificationCode(phoneNumber: String)

    suspend fun confirmVerificationCode(code: String)
}
