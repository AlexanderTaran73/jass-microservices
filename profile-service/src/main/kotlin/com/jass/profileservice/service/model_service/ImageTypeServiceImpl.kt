package com.jass.profileservice.service.model_service


import com.jass.profileservice.module.ImageType
import com.jass.profileservice.repository.ImageTypeRepository
import org.springframework.stereotype.Service

@Service
class ImageTypeServiceImpl(
    private val imageTypeRepository: ImageTypeRepository
): ImageTypeService {
    override fun findById(id: Int): ImageType? {
        return imageTypeRepository.findById(id).get()
    }
}