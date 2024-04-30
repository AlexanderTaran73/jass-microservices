package com.jass.eventservice.module

import com.jass.eventservice.module.type_dictionary.OrganizerRights
import jakarta.persistence.*

@Entity
@Table
class EventOrganizer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var userId: Int = 0

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn
    var organizerContacts: OrganizerContacts? = null

    @ManyToOne
    @JoinColumn
    var organizerRights: OrganizerRights? = null

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinTable
    var organizerActivityType: MutableList<OrganizerActivityType> = mutableListOf()
}