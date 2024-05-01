package com.jass.eventservice.service.module_service

import com.jass.eventservice.dto.Request.CreateEventRequest
import com.jass.eventservice.module.*
import com.jass.eventservice.repository.EventRepository
import com.jass.eventservice.repository.type_dictionary_repository.*
import org.springframework.stereotype.Service

@Service
class EventServiceImpl(
    private val eventRepository: EventRepository,
    private val accessToEventRepository: AccessToEventRepository,
    private val eventTypeRepository: EventTypeRepository,
    private val eventVisibilityRepository: EventVisibilityRepository,
    private val organizerRightsRepository: OrganizerRightsRepository,
    private val participantsVisibilityRepository: ParticipantsVisibilityRepository
): EventService {

    override fun create(id: Int, createEventRequest: CreateEventRequest): Event {
       return Event().also { event ->
            event.name = createEventRequest.name

            event.eventDescription = EventDescription().also { description ->
                description.description_text = createEventRequest.description?.description_text
                description.location = createEventRequest.description?.location
                description.data = createEventRequest.description?.data
                description.time = createEventRequest.description?.time
                description.participants_count = createEventRequest.description?.participants_count
            }

            event.eventSettings = EventSettings().also { settings ->
                settings.eventVisibility = eventVisibilityRepository.findByName(createEventRequest.settings?.eventVisibility.toString())
                    ?: eventVisibilityRepository.findByName("Closed")!!

                settings.accessToEvent = accessToEventRepository.findByName(createEventRequest.settings?.accessToEvent.toString())
                    ?: accessToEventRepository.findByName("ByRequest")!!

                settings.participantsVisibility = participantsVisibilityRepository.findByName(createEventRequest.settings?.participantsVisibility.toString())
                    ?: participantsVisibilityRepository.findByName("VISIBLE")!!
            }

            event.eventOrganizers.add(
                EventOrganizer().also { organizer ->
                    organizer.userId = id
                    organizer.organizerRights = organizerRightsRepository.findByName(createEventRequest.owner?.organizerRights.toString())
                    organizer.organizerContacts = OrganizerContacts().also { contacts ->
                        contacts.email = createEventRequest.owner?.organizerContacts?.email
                        contacts.phoneNumber = createEventRequest.owner?.organizerContacts?.phoneNumber
                        contacts.website = createEventRequest.owner?.organizerContacts?.website
                        contacts.telegram = createEventRequest.owner?.organizerContacts?.telegram
                        contacts.another_contact = createEventRequest.owner?.organizerContacts?.another_contact
                    }
                }
            )

            event.eventType = eventTypeRepository.findByName(createEventRequest.eventType.toString()) ?: eventTypeRepository.findByName("Other") // TODO: may be findById?

            eventRepository.save(event)
        }
    }

    override fun save(event: Event) {
        eventRepository.save(event)
    }

    override fun findById(id: Int): Event {
        return eventRepository.findById(id).get()
    }

    override fun findAll(): List<Event> {
        return eventRepository.findAll()
    }

    override fun delete(event: Event) {
        eventRepository.delete(event)
    }

    override fun findByEventOrganizersUserId(userId: Int): List<Event> {
        return eventRepository.findByEventOrganizersUserId(userId)
    }

    override fun deleteById(id: Int) {
        eventRepository.deleteById(id)
    }

    override fun deleteByIdNotIn(id: List<Int>) {
        eventRepository.deleteByIdNotIn(id)
    }
}