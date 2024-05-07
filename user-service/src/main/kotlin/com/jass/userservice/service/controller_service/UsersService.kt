package com.jass.userservice.service.controller_service

import com.jass.userservice.dto.CreateUserRequest
import com.jass.userservice.dto.ShortUserResponse
import com.jass.userservice.feign.ProfileService
import com.jass.userservice.service.model_service.UserAccountStatusService
import com.jass.userservice.service.model_service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UsersService(
    private val userService: UserService,
    private val profileService: ProfileService,
    private val userAccountStatusService: UserAccountStatusService
) {


    fun createUser(createUserRequest: CreateUserRequest): ResponseEntity<ShortUserResponse> {
        var user = userService.findByEmail(createUserRequest.email)
        if (user != null){
            if (user.status!!.id == 2 /*DELETED*/){
                user.status = userAccountStatusService.findById(0)
            }
            else return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }
        else user = userService.create(createUserRequest)

//        Create Profile
        profileService.createProfile(createUserRequest.email, user.id)

        return ResponseEntity(ShortUserResponse().userToShortUserResponse(user), HttpStatus.CREATED)
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