package com.jass.authservice.service.model_service

import com.jass.authservice.model.AuthUserData
import com.jass.authservice.repository.AuthUserDataRepository
import org.springframework.stereotype.Service

@Service
class AuthUserDataServiceImpl(
    private val authUserDataRepository: AuthUserDataRepository,
    private val userRoleService: UserRoleService
): AuthUserDataService {
    override fun create(email: String, password: String): AuthUserData {
        return AuthUserData().also { data ->
            data.email = email
            data.password = password
            data.roles.add(userRoleService.findById(0)!!)
            save(data)
        }
    }

    override fun save(authUserData: AuthUserData) {
        authUserDataRepository.save(authUserData)
    }

    override fun delete(authUserData: AuthUserData) {
        authUserDataRepository.delete(authUserData)
    }

    override fun findByEmail(email: String): AuthUserData? {
        return authUserDataRepository.findByEmail(email)
    }
}