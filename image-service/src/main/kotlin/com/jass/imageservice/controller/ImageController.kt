package com.jass.imageservice.controller

import com.jass.imageservice.service.controller_service.ImageService
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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

    @GetMapping("/getImage/{fileName}", produces = [MediaType.IMAGE_JPEG_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun getImage(@PathVariable fileName: String): FileSystemResource {
        return imageService.getImage(fileName)
    }

    @GetMapping("/getImageInfo")
    fun getImageInfo(@RequestParam type: String, @RequestParam ownerId: Int): ResponseEntity<Any> {
        return imageService.getImageInfo(type, ownerId)
    }
}