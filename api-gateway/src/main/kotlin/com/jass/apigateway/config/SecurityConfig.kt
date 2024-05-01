package com.jass.apigateway.config

import com.jass.apigateway.config.jwt.JwtFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class SecurityConfig(
    private val jwtFilter: JwtFilter
) {

    @Bean
    fun configure(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
            .csrf { it.disable() }
            .authorizeExchange { exchanges ->
                exchanges
//                    TODO: make settings
                    .pathMatchers("/api/v1/auth/**").permitAll()
                    .pathMatchers(
                        "/api/v1/user/admin/**",
                        "/api/v1/profile/admin/**"
                    ).hasAnyRole("ADMIN")
                    .pathMatchers(
                        "/api/v1/user/**",
                        "/api/v1/profile/**",
                        "/api/v1/image/getImage/**",
                        "/api/v1/event/**",
                    ).hasAnyRole("USER", "ADMIN")
            }
            .addFilterBefore(jwtFilter, SecurityWebFiltersOrder.HTTP_BASIC)

        return http.build()
    }
}