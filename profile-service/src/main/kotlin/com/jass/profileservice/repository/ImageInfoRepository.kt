package com.jass.profileservice.repository

import com.jass.profileservice.module.ImageInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageInfoRepository: JpaRepository<ImageInfo, Int> {
}