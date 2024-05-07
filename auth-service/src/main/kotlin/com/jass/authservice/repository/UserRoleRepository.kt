package com.jass.authservice.repository

import com.jass.authservice.model.UserRole
import org.springframework.data.jpa.repository.JpaRepository

interface UserRoleRepository: JpaRepository<UserRole, Int> {

    fun findByName(name: String): UserRole?
}