package com.jass.authservice.service.model_service

import com.jass.authservice.model.AuthUserData

interface AuthUserDataService {
    fun create(email: String, password: String): AuthUserData

    fun save(authUserData: AuthUserData)

    fun delete(authUserData: AuthUserData)

    fun findByEmail(email: String): AuthUserData?
}