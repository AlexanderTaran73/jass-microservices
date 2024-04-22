package com.jass.authservice.service.model_service

import com.jass.authservice.model.UserRole

interface UserRoleService {

    fun save(userRole: UserRole)

    fun findById(id: Int): UserRole?
}