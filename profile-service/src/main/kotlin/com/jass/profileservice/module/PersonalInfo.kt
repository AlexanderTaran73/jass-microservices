package com.jass.profileservice.module

import jakarta.persistence.*


@Entity
@Table
class PersonalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var firstName: String? = null

    @Column
    var lastName: String? = null

    @ManyToOne(optional = false, cascade = [CascadeType.ALL])
    @JoinColumn
    var gender: Gender? = null

    @Column
    var birthDate: String? = null
}