package com.jass.imageservice.service.model_service

import com.jass.imageservice.model.ImageInfo
import com.jass.imageservice.model.ImageType

interface ImageInfoService {

    fun create(fileName: String, type: String, ownerId: Int): ImageInfo

    fun save(imageInfo: ImageInfo)

    fun findById(id: Int): ImageInfo?

    fun findAllByOwnerIdAndType(ownerId: Int, type: String): List<ImageInfo>

    fun delete(imageInfo: ImageInfo)
}