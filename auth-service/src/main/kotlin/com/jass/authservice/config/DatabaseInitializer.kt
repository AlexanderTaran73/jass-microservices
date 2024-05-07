package com.jass.authservice.config

import com.jass.authservice.model.UserRole
import com.jass.authservice.repository.UserRoleRepository
import jakarta.transaction.Transactional
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DatabaseInitializer(
    private val userRoleRepository: UserRoleRepository
) : CommandLineRunner {

    @Transactional
    override fun run(vararg args: String?) {

        if (userRoleRepository.count() == 0L) {
            userRoleRepository.save(UserRole().also { it.id = 0; it.name = "ROLE_USER" })
            userRoleRepository.save(UserRole().also { it.id = 1; it.name = "ROLE_ADMIN" })
        }
    }
}