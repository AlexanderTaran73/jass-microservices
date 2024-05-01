package com.jass.eventservice.dto.Request

class OwnerDTO(
    override var organizerContacts: OrganizerContactsDTO,
    override var organizerActivityType: MutableList<String>,
): EventOrganizerDTO(organizerContacts, "OWNER", organizerActivityType)
