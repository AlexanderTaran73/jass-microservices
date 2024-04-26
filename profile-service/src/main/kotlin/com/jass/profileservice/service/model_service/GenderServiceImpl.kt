package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.Gender
import com.jass.profileservice.repository.GenderRepository
import org.springframework.stereotype.Service

@Service
class GenderServiceImpl(
    private val genderRepository: GenderRepository
): GenderService {

    override fun findById(id: Int): Gender? {
        return genderRepository.findById(id).get()
    }

    override fun findByName(name: String): Gender? {
        return genderRepository.findByName(name)
    }
}