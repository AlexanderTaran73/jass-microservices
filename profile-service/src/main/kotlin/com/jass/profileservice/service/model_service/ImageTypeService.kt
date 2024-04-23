package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.ImageInfo
import com.jass.profileservice.module.ImageType

interface ImageTypeService {

    fun findById(id: Int): ImageType?

}