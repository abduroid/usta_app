package com.example.ustaapp.core.model.data

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
