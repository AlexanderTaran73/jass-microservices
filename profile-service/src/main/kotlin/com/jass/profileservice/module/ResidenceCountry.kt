package com.jass.profileservice.module

import jakarta.persistence.*

@Entity
@Table
class ResidenceCountry {
    @Id
    var id: Int = 0

    @Column(unique = true)
    var name: String = ""
//  UNDEFINED
//  RUSSIA
//  GERMANY
//  ENGLAND
//  SPAIN
//  FRANCE
//  ITALY
//  USA
//  OTHER
}