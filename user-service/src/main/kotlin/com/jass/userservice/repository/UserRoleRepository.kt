package com.jass.userservice.repository

import com.jass.userservice.model.UserRole
import org.springframework.data.jpa.repository.JpaRepository

interface UserRoleRepository: JpaRepository<UserRole, Int> {

    fun findByName(name: String): UserRole?
}