package com.jass.imageservice.repository

import com.jass.imageservice.model.ImageInfo
import com.jass.imageservice.model.ImageType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageInfoRepository: JpaRepository<ImageInfo, Int> {

    fun findAllByOwnerIdAndType(ownerId: Int, type: ImageType): List<ImageInfo>
}