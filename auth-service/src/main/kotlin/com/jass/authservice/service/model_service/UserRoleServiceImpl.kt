package com.jass.authservice.service.model_service

import com.jass.authservice.model.UserRole
import com.jass.authservice.repository.UserRoleRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class UserRoleServiceImpl(
    private val userRoleRepository: UserRoleRepository
): UserRoleService {
    override fun save(userRole: UserRole) {
        userRoleRepository.save(userRole)
    }

    override fun findById(id: Int): UserRole? {
        return userRoleRepository.findById(id).orElse(null)
    }

}