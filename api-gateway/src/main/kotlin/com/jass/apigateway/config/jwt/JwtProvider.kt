package com.jass.apigateway.config.jwt


import com.jass.apigateway.config.CustomAbstractAuthenticationToken
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.util.stream.Collectors


@Component
class JwtProvider {

//    TODO: Make a secret key
    var accessJwtSecret = "accessJwtSecret"

    fun getAbstractAuthenticationToken(token: String?): AbstractAuthenticationToken? {
        val copyTolen = token.toString()
        try {
//          Verification for server token
            val roles = Jwts.parser().setSigningKey(accessJwtSecret).parseClaimsJws(token).body["roles"] as List<String>
            val abstractAuthenticationToken = CustomAbstractAuthenticationToken(
                roles.stream()
                    .map {
                        SimpleGrantedAuthority(it) }
                    .collect(Collectors.toList())
            )
            return abstractAuthenticationToken
        } catch (_: Exception) { }
        return null
    }

    fun getEmailFromToken(token: String?): String? {
        try {
            val claims = Jwts.parser().setSigningKey(accessJwtSecret).parseClaimsJws(token).body
            return claims.subject
        } catch (e: Exception) { }
        return null
    }
}