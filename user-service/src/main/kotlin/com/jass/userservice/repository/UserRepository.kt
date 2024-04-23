package com.jass.userservice.repository

import com.jass.userservice.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Int> {

    fun findByEmail(email: String): User?
}