package com.jass.authservice.repository

import com.jass.authservice.model.ChangePasswordData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChangePasswordDataRepository: JpaRepository<ChangePasswordData, Int> {

    fun findByEmail(email: String): ChangePasswordData?
}