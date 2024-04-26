package com.jass.profileservice.module

import jakarta.persistence.*

@Entity
@Table
class ProfileSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @ManyToOne(optional = false)
    @JoinColumn
    var profileVisibility: ProfileVisibility? = null

    @ManyToOne(optional = false)
    @JoinColumn
    var language: ProfileLanguage? = null

    @ManyToOne(optional = false)
    @JoinColumn
    var colorTheme: ProfileColorTheme? = null
//    TODO: add profile settings
}