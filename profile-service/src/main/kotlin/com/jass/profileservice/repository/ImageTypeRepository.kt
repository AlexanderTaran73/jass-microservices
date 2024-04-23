package com.jass.profileservice.repository

import com.jass.profileservice.module.ImageType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageTypeRepository: JpaRepository<ImageType, Int> {
}