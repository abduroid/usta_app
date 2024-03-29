package com.example.ustaapp.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.threeten.bp.LocalDateTime

@Serializable()
data class NetworkTokenPair(

    @SerialName("access_token")
    val accessToken: String,

    @SerialName("refresh_token")
    val refreshToken: String,

    @SerialName("access_token_expires_at")
    @Serializable(with = LocalDateTimeAsStringSerializer::class)
    val accessTokenExpiresAt: LocalDateTime,

    @SerialName("refresh_token_expires_at")
    @Serializable(with = LocalDateTimeAsStringSerializer::class)
    val refreshTokenExpiresAt: LocalDateTime,
)
