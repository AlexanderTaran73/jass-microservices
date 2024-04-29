package com.jass.imageservice.repository

import com.jass.imageservice.model.ImageType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageTypeRepository: JpaRepository<ImageType, Int> {

    fun findByName(name: String): ImageType?
}