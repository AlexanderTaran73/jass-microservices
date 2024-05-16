package com.jass.eventservice.service.controller_service

import com.jass.eventservice.dto.Request.EventOrganizerDTO
import com.jass.eventservice.feign.UserService
import com.jass.eventservice.module.EventOrganizer
import com.jass.eventservice.module.OrganizerActivityType
import com.jass.eventservice.module.OrganizerContacts
import com.jass.eventservice.module.Participant
import com.jass.eventservice.repository.OrganizerActivityTypeRepository
import com.jass.eventservice.repository.type_dictionary_repository.OrganizerRightsRepository
import com.jass.eventservice.service.module_service.EventService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class OrganizersService(
    private val eventService: EventService,
    private val userService: UserService,
    private val organizerRightsRepository: OrganizerRightsRepository,
    private val organizerActivityTypeRepository: OrganizerActivityTypeRepository
) {

    fun confirmParticipation(id: Int, eventId: Int, participantId: Int): ResponseEntity<HttpStatus> {
        if (userService.getUsersShortById(listOf(participantId)).body!![0] == null) return ResponseEntity(HttpStatus.BAD_REQUEST)
        eventService.findById(eventId)?.also { event ->

            for (i in event.eventOrganizers) {
                if (i.userId == id &&
                    i.organizerRights!!.name == "OWNER" ||
                    i.organizerRights!!.name == "CO-OWNER" ||
                    i.organizerRights!!.name == "EDITOR" ||
                    i.organizerRights!!.name == "HELPER"
                ) {
                    var participant: Participant? = null
                    event.participantsRequests.forEach {
                        if (it.userId == participantId) {
                            participant = it
                        }
                    }
                    if (participant == null) return ResponseEntity(HttpStatus.BAD_REQUEST)
                    event.participants.add(participant!!)
                    eventService.save(event)
                    return ResponseEntity(HttpStatus.NO_CONTENT)
                }
            }
        }
        return ResponseEntity(HttpStatus.FORBIDDEN)
    }

    fun addOrganizer(id: Int, eventId: Int, organizer: EventOrganizerDTO): ResponseEntity<HttpStatus> {
        if (userService.getUsersShortById(listOf(organizer.userId)).body!![0] == null) return ResponseEntity(HttpStatus.BAD_REQUEST)
        if (id == organizer.userId) return ResponseEntity(HttpStatus.BAD_REQUEST)
        if (organizer.organizerRights == "OWNER") return ResponseEntity(HttpStatus.BAD_REQUEST)
        eventService.findById(eventId)?.also { event ->

            for (i in event.eventOrganizers) {
                if (i.userId == id &&
                    i.organizerRights!!.name == "OWNER" ||
                    i.organizerRights!!.name == "CO-OWNER"
                ) {
                    if (event.eventOrganizers.any { it.userId == organizer.userId }) return ResponseEntity(HttpStatus.BAD_REQUEST)

                    event.eventOrganizers.add(EventOrganizer().also { eventOrganizer ->
                        eventOrganizer.userId = organizer.userId
                        eventOrganizer.organizerContacts = OrganizerContacts().also {
                            it.email = organizer.organizerContacts.email
                            it.phoneNumber = organizer.organizerContacts.phoneNumber
                            it.website = organizer.organizerContacts.website
                            it.telegram = organizer.organizerContacts.telegram
                            it.another_contact = organizer.organizerContacts.another_contact
                        }
                        eventOrganizer.organizerRights = organizerRightsRepository.findByName(organizer.organizerRights)
                        eventOrganizer.organizerActivityType = mutableListOf<OrganizerActivityType>().also { it ->
                            for (i in organizer.organizerActivityType) {
                                val orgAtcType = OrganizerActivityType().also { it.name = i }
                                it.add(orgAtcType)
                                organizerActivityTypeRepository.save(orgAtcType)
                            }
                        }
                    })
                    eventService.save(event)
                    return ResponseEntity(HttpStatus.CREATED)
                }
            }
            return ResponseEntity(HttpStatus.FORBIDDEN)
        }
        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    fun changeOrganizer(id: Int, eventId: Int, changeOrganizer: EventOrganizerDTO): ResponseEntity<HttpStatus> {
        if (userService.getUsersShortById(listOf(changeOrganizer.userId)).body!![0] == null) return ResponseEntity(HttpStatus.BAD_REQUEST)
        if (id == changeOrganizer.userId) return ResponseEntity(HttpStatus.BAD_REQUEST)
        if (changeOrganizer.organizerRights == "OWNER") return ResponseEntity(HttpStatus.BAD_REQUEST)
        eventService.findById(eventId)?.also { event ->

            for (i in event.eventOrganizers) {
                if (i.userId == id &&
                    i.organizerRights!!.name == "OWNER" ||
                    i.organizerRights!!.name == "CO-OWNER"
                ) {

                    event.eventOrganizers.forEach { organizer ->
                        if (organizer.userId == changeOrganizer.userId) {
                            organizer.organizerContacts = OrganizerContacts().also {
                                it.email = changeOrganizer.organizerContacts.email
                                it.phoneNumber = changeOrganizer.organizerContacts.phoneNumber
                                it.website = changeOrganizer.organizerContacts.website
                                it.telegram = changeOrganizer.organizerContacts.telegram
                                it.another_contact = changeOrganizer.organizerContacts.another_contact
                            }
                            organizer.organizerRights = organizerRightsRepository.findByName(changeOrganizer.organizerRights)
                            organizer.organizerActivityType = mutableListOf<OrganizerActivityType>().also { it ->
                                for (i in changeOrganizer.organizerActivityType) {
                                    val orgAtcType = OrganizerActivityType().also { it.name = i }
                                    it.add(orgAtcType)
                                    organizerActivityTypeRepository.save(orgAtcType)
                                }
                            }
                        }
                        eventService.save(event)
                        return ResponseEntity(HttpStatus.NO_CONTENT)
                    }
                    return ResponseEntity(HttpStatus.BAD_REQUEST)
                }
            }
            return ResponseEntity(HttpStatus.FORBIDDEN)
        }
        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

}