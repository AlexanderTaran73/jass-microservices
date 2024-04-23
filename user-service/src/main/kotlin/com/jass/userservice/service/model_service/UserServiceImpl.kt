package com.jass.userservice.service.model_service

import com.jass.userservice.model.User
import com.jass.userservice.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {

    override fun save(user: User) {
        userRepository.save(user)
    }

    override fun findAll(): List<User> {
        return userRepository.findAll()
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }
}