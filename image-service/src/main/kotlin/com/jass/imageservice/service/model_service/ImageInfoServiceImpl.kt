package com.jass.imageservice.service.model_service

import com.jass.imageservice.model.ImageInfo
import com.jass.imageservice.model.ImageType
import com.jass.imageservice.repository.ImageInfoRepository
import com.jass.imageservice.repository.ImageTypeRepository
import org.springframework.stereotype.Service

@Service
class ImageInfoServiceImpl(
    private val imageInfoRepository: ImageInfoRepository,
    private val imageTypeRepository: ImageTypeRepository
): ImageInfoService {
    override fun create(fileName: String, type: String, ownerId: Int): ImageInfo {
        val imageInfo = ImageInfo().also { info ->
            info.fileName = fileName
            info.type = imageTypeRepository.findByName(type)
            info.ownerId = ownerId
        }
        save(imageInfo)
        return imageInfo
    }

    override fun save(imageInfo: ImageInfo) {
        imageInfoRepository.save(imageInfo)
    }

    override fun findById(id: Int): ImageInfo? {
        return imageInfoRepository.findById(id).get()
    }

    override fun findAllByOwnerIdAndType(ownerId: Int, type: String): List<ImageInfo> {
        return imageInfoRepository.findAllByOwnerIdAndType(ownerId, imageTypeRepository.findByName(type)!!)
    }

    override fun delete(imageInfo: ImageInfo) {
        imageInfoRepository.delete(imageInfo)
    }

}