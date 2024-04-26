package com.jass.profileservice.config

import com.jass.profileservice.module.*
import com.jass.profileservice.repository.*
import jakarta.transaction.Transactional
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DatabaseInitializer(
    private val genderRepository: GenderRepository,
    private val profileColorThemeRepository: ProfileColorThemeRepository,
    private val profileLanguageRepository: ProfileLanguageRepository,
    private val profileVisibilityRepository: ProfileVisibilityRepository,
    private val residenceCountryRepository: ResidenceCountryRepository
) : CommandLineRunner {

    @Transactional
    override fun run(vararg args: String?) {

//        Gender
        if (genderRepository.count() == 0L) {
            genderRepository.saveAll(
                listOf(
                    Gender().also { it.id = 0; it.name = "UNDEFINED" },
                    Gender().also { it.id = 1; it.name = "MALE" },
                    Gender().also { it.id = 2; it.name = "FEMALE" },
                    Gender().also { it.id = 3; it.name = "OTHER" }
                )
            )
        }

//        ProfileColorTheme
        if (profileColorThemeRepository.count() == 0L) {
            profileColorThemeRepository.saveAll(
                listOf(
                    ProfileColorTheme().also { it.id = 0; it.name = "LIGHT" },
                    ProfileColorTheme().also { it.id = 1; it.name = "DARK" }
                )
            )
        }

//        ProfileLanguage
        if (profileLanguageRepository.count() == 0L) {
            profileLanguageRepository.saveAll(
                listOf(
                    ProfileLanguage().also { it.id = 0; it.name = "ENGLISH" },
                    ProfileLanguage().also { it.id = 1; it.name = "RUSSIAN" }
                )
            )
        }

//        ProfileVisibility
        if (profileVisibilityRepository.count() == 0L) {
            profileVisibilityRepository.saveAll(
                listOf(
                    ProfileVisibility().also { it.id = 0; it.name = "PUBLIC" },
                    ProfileVisibility().also { it.id = 1; it.name = "FRIENDS_ONLY" },
                    ProfileVisibility().also { it.id = 2; it.name = "CLOSED" }
                )
            )
        }

//        ResidenceCountry
        if (residenceCountryRepository.count() == 0L) {
            residenceCountryRepository.saveAll(
                listOf(
                    ResidenceCountry().also { it.id = 0; it.name = "UNDEFINED" },
                    ResidenceCountry().also { it.id = 1; it.name = "RUSSIA" },
                    ResidenceCountry().also { it.id = 2; it.name = "GERMANY" },
                    ResidenceCountry().also { it.id = 3; it.name = "ENGLAND" },
                    ResidenceCountry().also { it.id = 4; it.name = "SPAIN" },
                    ResidenceCountry().also { it.id = 5; it.name = "FRANCE" },
                    ResidenceCountry().also { it.id = 6; it.name = "ITALY" },
                    ResidenceCountry().also { it.id = 7; it.name = "USA" },
                    ResidenceCountry().also { it.id = 8; it.name = "OTHER" },

                )
            )
        }
    }
}