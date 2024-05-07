package com.jass.authservice.dto

class ShortUserResponse (
    var id: Int,
    val email: String,
    var status: UserAccountStatus?
)