package com.jass.userservice.service.controller_service

import com.jass.userservice.dto.CreateUserRequest
import com.jass.userservice.dto.ShortUserResponse
import com.jass.userservice.feign.ProfileService
import com.jass.userservice.model.User
import com.jass.userservice.service.model_service.UserRoleService
import com.jass.userservice.service.model_service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class UsersService(
    private val userService: UserService,
    private val userRoleService: UserRoleService,
    private val profileService: ProfileService
) {


    fun createUser(createUserRequest: CreateUserRequest): ResponseEntity<ShortUserResponse> {
        if (userService.findByEmail(createUserRequest.email) != null) return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        val user = User().also {
            it.email = createUserRequest.email
            it.password = createUserRequest.password
            it.createdAt = LocalDateTime.now()

            val userRole = userRoleService.findByName("ROLE_USER")!!
            it.roles.add(userRole)
        }
        userService.save(user)
//        Create Profile
        profileService.createProfile(createUserRequest.email, user.id)

        return ResponseEntity(ShortUserResponse().userToShortUserResponse(user), HttpStatus.CREATED)
    }

    fun getUsersShort(email: List<String>): ResponseEntity<MutableList<ShortUserResponse?>> {
        val users = mutableListOf<ShortUserResponse?>()
        email.forEach {
            val user = userService.findByEmail(it)
            if (user == null) users.add(null)
            else users.add(ShortUserResponse().userToShortUserResponse(user))
        }
        return ResponseEntity(users, HttpStatus.OK)
    }
}