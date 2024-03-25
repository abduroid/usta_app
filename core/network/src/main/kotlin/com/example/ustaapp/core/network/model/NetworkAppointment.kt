package com.example.ustaapp.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

@Serializable()
data class NetworkAppointment(
    val id: String,

    @SerialName("barber_id")
    val barberId: String,

    @SerialName("client_id")
    val clientId: String,

    @SerialName("client_first_name")
    val clientFirstName: String,

    @SerialName("client_last_name")
    val clientLastName: String,

    @Serializable(with = LocalDateTimeAsStringSerializer::class)
    @SerialName("created_date")
    val createdDateTime: LocalDateTime,

    @SerialName("date")
    @Serializable(with = LocalDateAsStringSerializer::class)
    val selectedDate: LocalDate,

    @SerialName("start_time")
    val startTime: Float,

    @SerialName("end_time")
    val endTime: Float,
)
