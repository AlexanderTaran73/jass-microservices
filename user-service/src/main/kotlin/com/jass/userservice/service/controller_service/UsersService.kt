package com.jass.userservice.service.controller_service

import com.jass.userservice.dto.CreateUserRequest
import com.jass.userservice.dto.ShortUserResponse
import com.jass.userservice.feign.ProfileService
import com.jass.userservice.service.model_service.UserAccountStatusService
import com.jass.userservice.service.model_service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@Service
class UsersService(
    private val userService: UserService,
    private val profileService: ProfileService,
    private val userAccountStatusService: UserAccountStatusService
) {


    fun createUser(createUserRequest: CreateUserRequest): ResponseEntity<HttpStatus> {
        var user = userService.findByEmail(createUserRequest.email)
        if (user != null){
            if (user.status!!.id == 2 /*DELETED*/){
                user.status = userAccountStatusService.findById(0)
            }
            else return ResponseEntity(null, HttpStatus.CONFLICT)
        }
        else user = userService.create(createUserRequest)

//        Create Profile
        if (profileService.createProfile(createUserRequest.email, user.id).statusCode != HttpStatus.CREATED) return ResponseEntity(null, HttpStatus.CONFLICT)

        return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest()
            .replacePath("/api/v1/user/getUsers/short/byId?id=")
            .pathSegment("{id}")
            .buildAndExpand(user.id)
            .toUri())
            .build();
    }

    fun getUsersShortByEmail(email: List<String>): ResponseEntity<MutableList<ShortUserResponse?>> {
        val users = mutableListOf<ShortUserResponse?>()
        email.forEach {
            val user = userService.findByEmail(it)
            if (user == null) users.add(null)
            else users.add(ShortUserResponse().userToShortUserResponse(user))
        }
        return ResponseEntity(users, HttpStatus.OK)
    }

    fun getUsersShortById(id: List<Int>): ResponseEntity<MutableList<ShortUserResponse?>> {
        val users = mutableListOf<ShortUserResponse?>()
        id.forEach {
            val user = userService.findById(it)
            if (user == null) users.add(null)
            else users.add(ShortUserResponse().userToShortUserResponse(user))
        }
        return ResponseEntity(users, HttpStatus.OK)
    }
}