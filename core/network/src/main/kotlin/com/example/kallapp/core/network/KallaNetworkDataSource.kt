package com.example.kallapp.core.network

interface KallaNetworkDataSource {
    suspend fun getSchedule(): List<Int>
}
