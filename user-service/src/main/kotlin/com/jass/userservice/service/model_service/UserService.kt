package com.jass.userservice.service.model_service

import com.jass.userservice.model.User

interface UserService {

    fun save(user: User)

    fun findAll(): List<User>

    fun findByEmail(email: String): User?
}