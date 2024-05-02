package com.jass.eventservice.feign

import com.jass.eventservice.dto.ShortUserResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "user-service")
interface UserService {

    @GetMapping("/api/v1/user/getUsers/short/byId")
    fun getUsersShortById(@RequestParam id: List<Int>): ResponseEntity<MutableList<ShortUserResponse?>>
}