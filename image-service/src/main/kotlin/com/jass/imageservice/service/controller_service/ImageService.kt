package com.jass.imageservice.service.controller_service

import com.jass.imageservice.service.model_service.ImageInfoService
import com.jass.imageservice.utils.formatFileName
import com.jass.imageservice.utils.getCurrentTimestamp
import com.jass.imageservice.utils.getFileNameAndExtension
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Paths

@Service
class ImageService(
    private val imageInfoService: ImageInfoService,
) {

//    TODO: change in the future
    val rootFolder: String = "D:/Projects/Jass/jass-microservices/image-service/src/main/resources/images/"


    fun saveImage(imageFile: MultipartFile, type: String, ownerId: Int): ResponseEntity<Any> {
        val bytes = imageFile.bytes
        val formattedTimestamp = getCurrentTimestamp(pattern = "dd_MM_yyyy_hh_mm_ss")
        val fileName = getGeneratedFileName(imageFile, prefix = "IMG", postFix = formattedTimestamp)
        val destination = getDestination(fileName)

        imageInfoService.create(fileName, type, ownerId)
        writeFileAtDestination(destination, bytes)

        return ResponseEntity(fileName, HttpStatus.CREATED)
    }

    fun getImage(fileName: String): FileSystemResource {
        val destination = getDestination(fileName)
        val file = File(destination)
        if (!file.isFile) throw FileNotFoundException("File with $fileName not found.")

        return FileSystemResource(file)
    }


    fun getImageInfo(type: String, ownerId: Int): ResponseEntity<Any> {
        return ResponseEntity(
            imageInfoService.findAllByOwnerIdAndType(ownerId, type),
            HttpStatus.OK
        )
    }


    private fun getDestination(fileName: String): String {
        return "$rootFolder${fileName.trim()}"
    }

    private fun getGeneratedFileName(
        imageFile: MultipartFile,
        prefix: String,
        postFix: String,
        prefixSep: String = "_",
        postfixSep: String = "_"
    ): String {
        val (fileName, fileExtension) = getFileNameAndExtension(imageFile)
        val formattedFileName = formatFileName(fileName)
        return "${prefix}${prefixSep}${formattedFileName}${postfixSep}${postFix}.${fileExtension}"
            .replace("(_{2,})".toRegex(), "_")
    }

    private fun writeFileAtDestination(destination: String, bytes: ByteArray) {
        val path = Paths.get(destination)
        Files.write(path, bytes)
    }
}