package com.jass.eventservice.dto



class CreateEventRequest {
    var name: String? = null
    var description: EventDescriptionDTO? = null
    var settings: EventSettingsDTO? = null
    var owner: OwnerDTO? = null
    var eventType: String? = null
}