package com.example.usta_app.core.model.data

data class Barber(
    val id: String,
    val firstName: String,
    val lastName: String,
    val username: String,
    val phoneNumber: String,
    val defaultHaircutDurationInMinutes: Int,
    val address: String,
    val barbershopId: String,
    val notificationToken: String,
)
