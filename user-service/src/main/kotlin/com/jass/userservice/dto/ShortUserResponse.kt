package com.jass.userservice.dto

import com.jass.userservice.model.User
import com.jass.userservice.model.UserAccountStatus

class ShortUserResponse {
    var id: Int = 0
    var email: String = ""
    var status: UserAccountStatus? = null


    fun userToShortUserResponse(user: User): ShortUserResponse{
        id = user.id
        email = user.email
        status = user.status
        return this
    }
}

