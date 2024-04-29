package com.jass.imageservice.controller

import com.jass.imageservice.service.controller_service.ImageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/image")
class ImageController(
    private val imageService: ImageService
) {
    @PostMapping("/saveImage")
    fun saveImage(@RequestParam("imageFile") imageFile: MultipartFile, @RequestParam type: String, @RequestParam ownerId: Int): ResponseEntity<Any> {
        return imageService.saveImage(imageFile, type, ownerId)
    }
}