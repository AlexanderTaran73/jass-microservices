package com.jass.imageservice.config


import com.jass.imageservice.model.ImageType
import com.jass.imageservice.repository.ImageTypeRepository
import jakarta.transaction.Transactional
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DatabaseInitializer(
    private val imageTypeRepository: ImageTypeRepository
) : CommandLineRunner {

    @Transactional
    override fun run(vararg args: String?) {

//        ImageType
        if (imageTypeRepository.count() < 5) {
            imageTypeRepository.saveAll(
                listOf(
                    ImageType().also{it.id = 0; it.name = "ProfileImage"},
                    ImageType().also{it.id = 1; it.name = "ProfileAvatar"},
                    ImageType().also{it.id = 2; it.name = "EventImage"},
                    ImageType().also{it.id = 3; it.name = "EventAvatar"},
                    ImageType().also{it.id = 4; it.name = "ProfilePostImage"}
                )
            )
        }
    }
}