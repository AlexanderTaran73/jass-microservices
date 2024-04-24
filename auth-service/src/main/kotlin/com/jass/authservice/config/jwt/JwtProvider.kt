package com.jass.authservice.config.jwt


import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*



@Component
class JwtProvider {

//    TODO: Make a secret key
    var accessJwtSecret = "accessJwtSecret"
    var refreshJwtSecret = "refreshJwtSecret"

    fun generateAccessToken(email: String?, roles: List<String>): String {



        val date = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant())
        return Jwts.builder()
            .setSubject(email)
            .claim("roles", roles)
            .setExpiration(date)
            .signWith(SignatureAlgorithm.HS512, accessJwtSecret)
            .compact()
    }

    fun generateRefreshToken(email: String?): String {
        val date = Date.from(LocalDate.now().plusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant())
        return Jwts.builder()
            .setSubject(email)
            .setExpiration(date)
            .signWith(SignatureAlgorithm.HS512, refreshJwtSecret)
            .compact()
    }


    fun refreshTokenVerification(token: String): String? {
        return Jwts
            .parser()
            .setSigningKey(refreshJwtSecret)
            .parseClaimsJws(token)
            .body
            .subject
    }
}