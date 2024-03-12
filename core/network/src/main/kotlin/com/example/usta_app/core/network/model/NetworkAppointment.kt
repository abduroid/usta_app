package com.example.usta_app.core.network.model

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

data class NetworkAppointment(
    val id: String,
    val barberId: String,
    val clientId: String,
    val clientFirstName: String,
    val clientLastName: String,
    val createdDateTime: LocalDateTime,
    val selectedDate: LocalDate,
    val startTime: Float,
    val endTime: Float,
)
