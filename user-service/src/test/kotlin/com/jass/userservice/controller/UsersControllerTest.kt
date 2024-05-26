package com.jass.userservice.controller

import com.jass.userservice.feign.ProfileService
import com.jass.userservice.model.User
import com.jass.userservice.model.UserAccountStatus
import com.jass.userservice.repository.UserRepository
import com.jass.userservice.service.controller_service.UsersService
import com.jass.userservice.service.model_service.UserAccountStatusService
import com.jass.userservice.service.model_service.UserService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Transactional
@ExtendWith(value = [TestContainersExtension::class])
class UsersControllerTest: AbstractIntegrationTest() {

    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var userAccountStatusService: UserAccountStatusService
    @Autowired
    lateinit var userRepository: UserRepository


    @MockK
    var profileService = mockk<ProfileService>()

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(UsersController(
            UsersService(
                userService,
                profileService,
                userAccountStatusService
            )
        )
        ).build()
    }

    @Test
    fun `createUser user is successfully created`() {

        every { profileService.createProfile(any(), any()) } returns ResponseEntity(HttpStatus.CREATED)

        var userId = mockMvc.perform(
            post("/api/v1/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"email\": \"test@example.com\" }")
        )
            .andExpect(status().isCreated)
            .andReturn().response.getHeaderValue("Location").toString().split("/api/v1/user/getUsers/short/byId%3Fid=").last().toInt()

        assert(!userRepository.findById(userId).isEmpty)

        verify(exactly = 1) { profileService.createProfile(any(), any()) }


    }

    @Test
    fun `createUser user with deleted status`() {
        userRepository.save(User().apply {
            id = 1
            email = "test@example.com"
            status = UserAccountStatus().apply {
                id = 2
                name = "DELETED"
            }
            createdAt = LocalDateTime.now()
        })

        every { profileService.createProfile(any(), any()) } returns ResponseEntity(HttpStatus.CREATED)


        var userId = mockMvc.perform(
            post("/api/v1/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"email\": \"test@example.com\" }")
        )
            .andExpect(status().isCreated)
            .andReturn().response.getHeaderValue("Location").toString().split("/api/v1/user/getUsers/short/byId%3Fid=").last().toInt()

        assert(!userRepository.findById(userId).isEmpty)

        verify(exactly = 1) { profileService.createProfile(any(), any()) }
    }
//
    @Test
    fun `create user with busy email`() {
        userRepository.save(User().apply {
            id = 1
            email = "test@example.com"
            status = UserAccountStatus().apply {
                id = 0
                name = "ACTIVE"
            }
        })


        mockMvc.perform(
            post("/api/v1/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"email\": \"test@example.com\" }")
        )
            .andExpect(status().isConflict)
    }
}
