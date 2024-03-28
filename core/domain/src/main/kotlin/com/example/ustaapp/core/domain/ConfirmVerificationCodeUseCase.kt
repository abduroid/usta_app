package com.example.ustaapp.core.domain

import com.example.ustaapp.core.data.repository.AuthRepository
import javax.inject.Inject

class ConfirmVerificationCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(phoneNumber: String, code: String) =
        authRepository.confirmVerificationCode(phoneNumber, code)
}
