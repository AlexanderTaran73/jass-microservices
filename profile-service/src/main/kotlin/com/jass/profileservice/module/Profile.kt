package com.jass.profileservice.module

import jakarta.persistence.*


@Entity
@Table
class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column(unique = true)
    var userEmail: String = ""

    @Column
    var userName: String = ""

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn
    var personal_info: PersonalInfo = PersonalInfo()

//    TODO: move to image-service
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn
    var images: MutableList<ImageInfo?> = mutableListOf()

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn
    var profile_settings: ProfileSettings = ProfileSettings()
}