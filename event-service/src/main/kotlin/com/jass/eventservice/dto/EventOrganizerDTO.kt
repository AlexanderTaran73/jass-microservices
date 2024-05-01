package com.jass.eventservice.dto

open class EventOrganizerDTO (
    open var organizerContacts: OrganizerContactsDTO,
    open var organizerRights: String,
    open var organizerActivityType: MutableList<String>,
)