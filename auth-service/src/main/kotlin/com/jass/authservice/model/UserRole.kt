package com.jass.authservice.model

import jakarta.persistence.*
import lombok.AllArgsConstructor


@Entity
@Table(name="user_role")
class UserRole {
    @Id
    var id: Int = 0

    @Column
    var name: String = ""
//    ROLE_USER,
//    ROLE_ADMIN
}