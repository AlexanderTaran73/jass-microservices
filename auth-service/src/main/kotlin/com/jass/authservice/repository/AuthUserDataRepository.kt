package com.jass.authservice.repository

import com.jass.authservice.model.AuthUserData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface AuthUserDataRepository: JpaRepository<AuthUserData, Int> {

    fun findByEmail(email: String): AuthUserData?
}