package com.example.usta_app.core.network

import com.example.usta_app.core.network.model.NetworkAppointment
import org.threeten.bp.LocalDate

interface UstaNetworkDataSource {
    suspend fun getSchedule(from: LocalDate, to: LocalDate): List<NetworkAppointment>
}
