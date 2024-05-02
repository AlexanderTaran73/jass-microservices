package com.jass.eventservice.dto.Request

open class EventOrganizerDTO (
    open var userId: Int,
    open var organizerContacts: OrganizerContactsDTO,
    open var organizerRights: String,
    open var organizerActivityType: MutableList<String>,
)