package com.jass.apigateway.config

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class CustomAbstractAuthenticationToken(
    authorities: Collection<GrantedAuthority>
) : AbstractAuthenticationToken(authorities){
    private var principal: Any? = null

    init {
        super.setAuthenticated(true)
    }

    override fun getCredentials(): Any? {
        return null
    }

    override fun getPrincipal(): Any? {
        return principal
    }

    fun setPrincipal(principal: Any) {
        this.principal = principal
    }

}