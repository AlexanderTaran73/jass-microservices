package com.jass.profileservice.repository

import com.jass.profileservice.module.ResidenceCountry
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ResidenceCountryRepository: JpaRepository<ResidenceCountry, Int> {
    fun findByName(name: String): ResidenceCountry?
}