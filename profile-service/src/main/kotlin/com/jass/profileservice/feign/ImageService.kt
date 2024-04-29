package com.jass.profileservice.feign

import com.jass.profileservice.dto.ImageInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@FeignClient(name = "image-service")
interface ImageService {

    @PostMapping("/api/v1/image/saveImage")
    fun saveImage(@RequestParam("imageFile") imageFile: MultipartFile, @RequestParam type: String, @RequestParam ownerId: Int): ResponseEntity<Any>

    @GetMapping("/api/v1/image/getImageInfo")
    fun getImageInfo(@RequestParam type: String, @RequestParam ownerId: Int): ResponseEntity<List<ImageInfoResponse>>

}