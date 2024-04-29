package com.jass.imageservice.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getCurrentTimestamp(pattern: String? = null, dateTimeFormatter: DateTimeFormatter? = null): String {
    val timestamp = LocalDateTime.now()
    if ((pattern == null) and (dateTimeFormatter == null)) return timestamp.toString()
    val formatter = dateTimeFormatter ?: DateTimeFormatter.ofPattern(pattern)
    return formatter.format(timestamp)
}