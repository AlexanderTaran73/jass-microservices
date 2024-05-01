package com.jass.eventservice.feign


import com.jass.eventservice.dto.ImageInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@FeignClient(name = "image-service")
interface ImageService {

    @PostMapping("/api/v1/image/saveImage", consumes = ["multipart/form-data"])
    fun saveImage(
        @RequestPart("imageFile") imageFile: MultipartFile,
        @RequestPart("type") type: String,
        @RequestPart("ownerId") ownerId: Int
    ): ResponseEntity<Any>

    @GetMapping("/api/v1/image/getImageInfo")
    fun getImageInfo(@RequestParam type: String, @RequestParam ownerId: Int): ResponseEntity<List<ImageInfoResponse>>

    @DeleteMapping("/api/v1/image/deleteImage/{fileName}")
    fun deleteImage(@PathVariable fileName: String): ResponseEntity<Any>
}