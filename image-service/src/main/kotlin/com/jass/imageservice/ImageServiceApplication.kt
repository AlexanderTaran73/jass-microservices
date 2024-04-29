package com.jass.imageservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients


@SpringBootApplication
@EnableFeignClients
class ImageServiceApplication

fun main(args: Array<String>) {
    runApplication<ImageServiceApplication>(*args)
}
