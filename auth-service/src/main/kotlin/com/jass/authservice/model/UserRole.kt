package com.jass.authservice.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor


@Entity
@Table(name="user_role")
class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var name: String = ""
}