package com.jass.authservice.service.model_service

import com.jass.authservice.model.User

interface UserService {

    fun save(user: User)

    fun findAll(): List<User>

    fun findByEmail(email: String): User?
}