package com.jass.profileservice.repository

import com.jass.profileservice.module.Gender
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GenderRepository: JpaRepository<Gender, Int> {
    fun findByName(name: String): Gender?
}