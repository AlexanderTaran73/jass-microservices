package com.jass.imageservice.utils

import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile

fun formatFileName(fileName: String) =
    fileName
        .replace(" ", "")
        .replace("([ ():])".toRegex(), "_")

fun getFileNameAndExtension(imageFile: MultipartFile): Pair<String, String> {
    val fileNameWithExtension = StringUtils.getFilename(imageFile.originalFilename)
    val fileExtension = StringUtils.getFilenameExtension(fileNameWithExtension)

    if (fileNameWithExtension == null || fileExtension == null) throw Exception("Invalid file, ${imageFile.originalFilename}")

    val fileName = removeFileExtension(fileNameWithExtension)

    return Pair(fileName, fileExtension)
}

fun removeFileExtension(filename: String, removeAllExtensions: Boolean = true): String {
    if (filename.isEmpty()) return filename

    val extPattern = "(?<!^)[.]" + if (removeAllExtensions) ".*" else "[^.]*$"
    return filename.replace(extPattern.toRegex(), "")
}