package com.jass.userservice.config

import com.jass.userservice.model.UserAccountStatus
import com.jass.userservice.repository.UserAccountStatusRepository
import jakarta.transaction.Transactional
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DatabaseInitializer(
    private val userAccountStatusRepository: UserAccountStatusRepository
) : CommandLineRunner {

    @Transactional
    override fun run(vararg args: String?) {

        if (userAccountStatusRepository.count() < 3L) {
            userAccountStatusRepository.save(UserAccountStatus().also { it.id = 0; it.name = "DEFAULT" })
            userAccountStatusRepository.save(UserAccountStatus().also { it.id = 1; it.name = "BANNED" })
            userAccountStatusRepository.save(UserAccountStatus().also { it.id = 2; it.name = "DELETED" })
        }
    }
}