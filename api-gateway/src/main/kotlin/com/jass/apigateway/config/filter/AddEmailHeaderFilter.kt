package com.jass.apigateway.config.filter

import com.jass.apigateway.config.jwt.JwtProvider
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.ZoneId
import java.util.*


@Component
class AddEmailAndIdHeaderFilter(
    private val jwtProvider: JwtProvider
) : AbstractGatewayFilterFactory<Any>() {

//    TODO: Make a secret key
    private val SECRET_GATEWAY_KEY = "SECRET_GATEWAY_KEY"

    override fun apply(config: Any): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request = exchange.request
            val authorizationHeader = request.headers.getFirst("Authorization")
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                val token = authorizationHeader.substring(7)
                val email = jwtProvider.getEmailFromToken(token)
                val id = jwtProvider.getIdFromToken(token)
                val modifiedRequest = exchange.request.mutate()
                    .header("User-Email", email)
                    .header("User-Id", id.toString())
                    .header("Authorization", "Bearer " + generateGatewayToken())
                    .build()
                chain.filter(exchange.mutate().request(modifiedRequest).build())
            } else {
                chain.filter(exchange)
            }
        }
    }

//    TODO: Think about how to use it
    fun generateGatewayToken(): String {
        val date = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant())
        return Jwts.builder()
            .setExpiration(date)
            .signWith(SignatureAlgorithm.HS512, SECRET_GATEWAY_KEY)
            .compact()
    }
}