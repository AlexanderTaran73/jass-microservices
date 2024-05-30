package com.jass.eventservice.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@Component
class JwtProvider {

//    TODO: Make a secret key
    val SECRET_EVENT_KEY = "SECRET_EVENT_KEY"

    fun generateEventToken(eventId: Int, tokenType: String): String {
        val date = Date.from(LocalDate.now().plusDays(5).atStartOfDay(ZoneId.systemDefault()).toInstant())
        return Jwts.builder()
            .setSubject(eventId.toString())
            .claim("tokenType", tokenType) // TODO: test token type data
            .setExpiration(date)
            .signWith(SignatureAlgorithm.HS512, SECRET_EVENT_KEY)
            .compact()
    }

    fun decodeEventToken(token: String): Jws<Claims> {
        return Jwts.parser().setSigningKey(SECRET_EVENT_KEY).parseClaimsJws(token)
    }
}