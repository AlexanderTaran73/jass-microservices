package com.jass.eventservice.dto.Request

class OwnerDTO(
    override var organizerContacts: OrganizerContactsDTO,
    override var organizerActivityType: MutableList<String>,
): EventOrganizerDTO(0, organizerContacts, "OWNER", organizerActivityType)
