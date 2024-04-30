package com.jass.userservice.dto

import com.jass.userservice.model.User
import com.jass.userservice.model.UserRole

class ShortUserResponse {
    var id: Int = 0
    var email: String = ""
    var password: String = ""
    var roles: MutableList<UserRole> = mutableListOf()

    fun userToShortUserResponse(user: User): ShortUserResponse{
        id = user.id
        email = user.email
        password = user.password
        roles = user.roles
        return this
    }
}

