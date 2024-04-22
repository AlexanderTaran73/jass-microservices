package com.jass.authservice.service.model_service

import com.jass.authservice.model.RegistrationData
import com.jass.authservice.repository.RegistrationDataRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
@Transactional
class RegistrationDataServiceImpl(
    private val registrationDataRepository: RegistrationDataRepository
): RegistrationDataService {
    override fun save(registrationData: RegistrationData) {
        registrationDataRepository.save(registrationData)
    }

    override fun delete(registrationData: RegistrationData) {
        registrationDataRepository.delete(registrationData)
    }

    override fun findByEmail(email: String): RegistrationData? {
        return registrationDataRepository.findByEmail(email)
    }
}