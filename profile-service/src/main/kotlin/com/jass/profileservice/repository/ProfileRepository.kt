package com.jass.profileservice.repository

import com.jass.profileservice.module.Profile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileRepository: JpaRepository<Profile, Int> {

    fun findByUserEmail(userEmail: String): Profile?
}