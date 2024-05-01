package com.jass.eventservice.config

import com.jass.eventservice.module.type_dictionary.*
import com.jass.eventservice.repository.type_dictionary_repository.*
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DatabaseInitializer(
    private val eventQuestionTypeRepository: EventQuestionTypeRepository,
    private val eventTypeRepository: EventTypeRepository,
    private val organizerRightsRepository: OrganizerRightsRepository,
    private val eventVisibilityRepository: EventVisibilityRepository,
    private val accessToEventRepository: AccessToEventRepository,
    private val participantsVisibilityRepository: ParticipantsVisibilityRepository
): CommandLineRunner {
    override fun run(vararg args: String?) {
//        EventQuestionType
        if (eventQuestionTypeRepository.count() < 2L) {
            eventQuestionTypeRepository.saveAll(
                listOf(
                    EventQuestionType().also { it.id = 0; it.name = "CLOSED" },
                    EventQuestionType().also { it.id = 1; it.name = "OPEN" },
                )
            )
        }

//        EventType
        if (eventTypeRepository.count() < 5L) {
            eventTypeRepository.saveAll(
                listOf(
                    EventType().also { it.id = 0; it.name = "Party" },
                    EventType().also { it.id = 1; it.name = "Celebration" },
                    EventType().also { it.id = 2; it.name = "Meeting" },
                    EventType().also { it.id = 3; it.name = "CorporateParty" },
                    EventType().also { it.id = 4; it.name = "Other" },
                )
            )
        }


//        OrganizerRights
        if (organizerRightsRepository.count() < 4L) {
            organizerRightsRepository.saveAll(
                listOf(
                    OrganizerRights().also { it.id = 0; it.name = "OWNER" },
                    OrganizerRights().also { it.id = 1; it.name = "CO-OWNER" },
                    OrganizerRights().also { it.id = 2; it.name = "EDITOR" },
                    OrganizerRights().also { it.id = 3; it.name = "HELPER" },
                )
            )
        }

//        EventVisibility
        if (eventVisibilityRepository.count() < 3L) {
            eventVisibilityRepository.saveAll(
                listOf(
                    EventVisibility().also { it.id = 0; it.name = "Public" },
                    EventVisibility().also { it.id = 1; it.name = "OrganizerFriendsOnly" },
                    EventVisibility().also { it.id = 2; it.name = "Closed" },
                )
            )
        }

//        AccessToEvent
        if (accessToEventRepository.count() < 3L) {
            accessToEventRepository.saveAll(
                listOf(
                    AccessToEvent().also { it.id = 0; it.name = "Open" },
                    AccessToEvent().also { it.id = 1; it.name = "ByRequest" },
                    AccessToEvent().also { it.id = 2; it.name = "ByPayment" },
                )
            )
        }

//        ParticipantsVisibility
        if (participantsVisibilityRepository.count() < 2L) {
            participantsVisibilityRepository.saveAll(
                listOf(
                    ParticipantsVisibility().also { it.id = 0; it.name = "VISIBLE" },
                    ParticipantsVisibility().also { it.id = 1; it.name = "INVISIBLE" },
                )
            )

        }

    }
}