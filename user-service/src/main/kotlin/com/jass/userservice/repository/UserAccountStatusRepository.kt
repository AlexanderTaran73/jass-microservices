package com.jass.userservice.repository

import com.jass.userservice.model.UserAccountStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserAccountStatusRepository: JpaRepository<UserAccountStatus, Int> {

    fun findByName(name: String): UserAccountStatus?
}