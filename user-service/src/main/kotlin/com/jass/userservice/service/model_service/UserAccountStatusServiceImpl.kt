package com.jass.userservice.service.model_service

import com.jass.userservice.model.UserAccountStatus
import com.jass.userservice.repository.UserAccountStatusRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class UserAccountStatusServiceImpl(
    private val userRoleRepository: UserAccountStatusRepository
): UserAccountStatusService {
    override fun save(userRole: UserAccountStatus) {
        userRoleRepository.save(userRole)
    }

    override fun findById(id: Int): UserAccountStatus? {
        return userRoleRepository.findById(id).orElse(null)
    }

    override fun findByName(name: String): UserAccountStatus? {
        return userRoleRepository.findByName(name)
    }

}