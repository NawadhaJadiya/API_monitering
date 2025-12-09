package com.example.serviceMoniter.meta.service

import com.example.serviceMoniter.meta.model.User
import com.example.serviceMoniter.meta.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    
    fun authenticateUser(email: String, password: String): User? {
        val user = userRepository.findByEmail(email)
        return if (user != null && user.password == password) {
            user
        } else {
            null
        }
    }
}


