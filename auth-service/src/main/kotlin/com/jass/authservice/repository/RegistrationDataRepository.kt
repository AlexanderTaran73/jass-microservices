package com.jass.authservice.repository

import com.jass.authservice.model.RegistrationData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RegistrationDataRepository: JpaRepository<RegistrationData, Int> {

    fun findByEmail(email: String): RegistrationData?
}