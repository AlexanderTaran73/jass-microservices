package com.jass.eventservice.module.type_dictionary

import jakarta.persistence.*

@Entity
@Table
class OrganizerRights {
    @Id
    var id: Int = 0

    @Column
    var name: String = ""
//    OWNER,
//    CO-OWNER,
//    EDITOR,
//    HELPER


}