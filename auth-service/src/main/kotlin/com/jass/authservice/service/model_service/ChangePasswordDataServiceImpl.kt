package com.jass.authservice.service.model_service

import com.jass.authservice.model.ChangePasswordData
import com.jass.authservice.repository.ChangePasswordDataRepository
import org.springframework.stereotype.Service

@Service
class ChangePasswordDataServiceImpl(
    private val changePasswordDataRepository: ChangePasswordDataRepository
): ChangePasswordDataService {
    override fun save(changePasswordData: ChangePasswordData) {
        changePasswordDataRepository.save(changePasswordData)
    }

    override fun delete(changePasswordData: ChangePasswordData) {
        changePasswordDataRepository.delete(changePasswordData)
    }

    override fun findByEmail(email: String): ChangePasswordData? {
        return changePasswordDataRepository.findByEmail(email)
    }
}