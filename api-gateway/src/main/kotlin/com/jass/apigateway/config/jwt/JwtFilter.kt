package com.jass.apigateway.config.jwt


import org.springframework.http.HttpHeaders
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono


@Component
class JwtFilter(
    private val jwtProvider: JwtProvider
) : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val token = getTokenFromRequest(exchange.request)
//      TODO: разобраться как эта хрень рботает
//        https://github.com/hantsy/spring-reactive-jwt-sample/blob/master/docs/GUIDE.md

        if (token != null && jwtProvider.getAbstractAuthenticationToken(token) != null) {
            return Mono.fromCallable { jwtProvider.getAbstractAuthenticationToken(token) }
                .flatMap { authentication ->
                    chain.filter(exchange)
                        .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication))
                }
        }

        return chain.filter(exchange)
    }

    fun getTokenFromRequest(request: ServerHttpRequest): String? {
        val bearer = request.headers.getFirst(HttpHeaders.AUTHORIZATION)
        return if (bearer != null && bearer.startsWith("Bearer ")) {
            bearer.substring(7)
        } else null
    }
}


