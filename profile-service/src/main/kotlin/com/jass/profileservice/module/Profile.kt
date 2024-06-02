package com.jass.profileservice.module

import jakarta.persistence.*


@Entity
@Table
class Profile {
    @Id
    var id: Int = 0

    @Column(unique = true)
    var userEmail: String = ""

    @Column
    var userName: String = ""

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn
    var personal_info: PersonalInfo = PersonalInfo()

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn
    var profile_settings: ProfileSettings = ProfileSettings()

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable
    var friends: MutableSet<Profile> = mutableSetOf()

    @OneToMany
    @JoinColumn
    var posts: MutableList<Post> = mutableListOf()
//    TODO: add createdAt and lastUpdatedAt
}