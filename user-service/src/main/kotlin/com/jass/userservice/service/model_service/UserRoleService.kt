package com.jass.userservice.service.model_service

import com.jass.userservice.model.UserRole

interface UserRoleService {

    fun save(userRole: UserRole)

    fun findById(id: Int): UserRole?
}