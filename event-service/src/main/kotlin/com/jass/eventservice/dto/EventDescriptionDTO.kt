package com.jass.eventservice.dto

import jakarta.persistence.Column

class EventDescriptionDTO {
    var description_text: String? = null // max length 1000 TODO: check
    var location: String? = null
    var data: String? = null
    var time: String? = null
    var participants_count: String? = null
}