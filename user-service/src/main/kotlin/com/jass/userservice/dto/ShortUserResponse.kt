package com.jass.userservice.dto

import com.jass.userservice.model.User
import com.jass.userservice.model.UserRole

class ShortUserResponse {
    var email: String = ""
    var password: String = ""
    var roles: MutableList<UserRole> = mutableListOf()

    fun userToShortUserResponse(user: User): ShortUserResponse{
        email = user.email
        password = user.password
        roles = user.roles
        return this
    }
}

