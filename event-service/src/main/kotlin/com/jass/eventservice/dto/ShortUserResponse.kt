package com.jass.eventservice.dto

import jakarta.persistence.Column
import jakarta.persistence.Id


class ShortUserResponse {
    var id: Int = 0
    var email: String = ""
    var password: String = ""
    var roles: MutableList<UserRole> = mutableListOf()
}

class UserRole {
    var id: Int = 0
    var name: String = ""
}