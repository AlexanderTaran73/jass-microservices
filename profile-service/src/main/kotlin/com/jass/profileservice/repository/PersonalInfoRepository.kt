package com.jass.profileservice.repository

import com.jass.profileservice.module.PersonalInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonalInfoRepository: JpaRepository<PersonalInfo, Int> {
}