package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.ResidenceCountry

interface ResidenceCountryService {

    fun findByName(name: String): ResidenceCountry?
}