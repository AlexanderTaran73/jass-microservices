package com.jass.apigateway.config.filter

import com.jass.apigateway.config.jwt.JwtProvider
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component




@Component
class AddEmailHeaderFilter(
    private val jwtProvider: JwtProvider
) : AbstractGatewayFilterFactory<Any>() {

    override fun apply(config: Any): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request = exchange.request
            val authorizationHeader = request.headers.getFirst("Authorization")
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                val token = authorizationHeader.substring(7)
                val email = jwtProvider.getEmailFromToken(token)
                val modifiedRequest = exchange.request.mutate()
                    .header("User-Email", email)
                    .build()
                chain.filter(exchange.mutate().request(modifiedRequest).build())
            } else {
                chain.filter(exchange)
            }
        }
    }
}