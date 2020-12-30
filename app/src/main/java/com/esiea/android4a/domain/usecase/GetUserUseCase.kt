package com.esiea.android4a.domain.usecase

import com.esiea.android4a.data.repository.UserRepository
import com.esiea.android4a.domain.entity.User

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    suspend fun invoke(email: String, password: String) : User?{
        return userRepository.getUser(email,password)
    }
}