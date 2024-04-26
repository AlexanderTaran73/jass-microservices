package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.ResidenceCountry
import com.jass.profileservice.repository.ResidenceCountryRepository
import org.springframework.stereotype.Service

@Service
class ResidenceCountryServiceImpl(
    private val residenceCountryRepository: ResidenceCountryRepository
): ResidenceCountryService {
    override fun findByName(name: String): ResidenceCountry? {
        return residenceCountryRepository.findByName(name)
    }
}