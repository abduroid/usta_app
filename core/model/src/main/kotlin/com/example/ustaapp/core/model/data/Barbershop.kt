package com.example.ustaapp.core.model.data

data class Barbershop(
    val id: String,
    val name: String,
    val address: String,
    val barbers: List<Barber>,
)
