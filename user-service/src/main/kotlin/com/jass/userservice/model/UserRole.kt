package com.jass.userservice.model

import jakarta.persistence.*



@Entity
@Table(name="user_role")
class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var name: String = ""
}