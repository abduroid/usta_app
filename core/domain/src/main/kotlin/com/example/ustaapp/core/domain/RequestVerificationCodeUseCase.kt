package com.example.ustaapp.core.domain

import com.example.ustaapp.core.data.repository.AuthRepository
import javax.inject.Inject

class RequestVerificationCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(phoneNumber: String) {
        authRepository.requestVerificationCode(phoneNumber)
    }
}
