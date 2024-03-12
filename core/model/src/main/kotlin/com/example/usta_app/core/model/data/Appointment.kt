package com.example.usta_app.core.model.data

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

data class Appointment(
    val id: String,
    val clientId: String,
    val clientFirstName: String,
    val clientLastName: String,
//    val createdDateTime: LocalDateTime,
//    val selectedDate: LocalDate,
    val startTime: Float,
    val endTime: Float,
)
