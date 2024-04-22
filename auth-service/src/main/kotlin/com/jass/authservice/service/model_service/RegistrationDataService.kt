package com.jass.authservice.service.model_service

import com.jass.authservice.model.RegistrationData

interface RegistrationDataService {

    fun save(registrationData: RegistrationData)

    fun delete(registrationData: RegistrationData)

    fun findByEmail(email: String): RegistrationData?
}
