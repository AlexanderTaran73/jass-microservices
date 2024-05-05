package com.jass.userservice.service.model_service

import com.jass.userservice.dto.CreateUserRequest
import com.jass.userservice.model.User

interface UserService {

    fun create(createUserRequest: CreateUserRequest): User

    fun save(user: User)

    fun delete(user: User)

    fun findAll(): List<User>

    fun findByEmail(email: String): User?

    fun findById(id: Int): User?
}