package com.jass.authservice.repository

import com.jass.authservice.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Int> {

    fun findByEmail(email: String): User?
}