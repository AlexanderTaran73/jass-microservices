package com.jass.eventservice.dto

class OwnerDTO(
    override var organizerContacts: OrganizerContactsDTO,
    override var organizerActivityType: MutableList<String>,
): EventOrganizerDTO(organizerContacts, "OWNER", organizerActivityType)
