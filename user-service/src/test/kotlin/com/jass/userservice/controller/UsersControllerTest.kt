package com.jass.userservice.controller

import com.jass.userservice.dto.CreateUserRequest
import com.jass.userservice.dto.ShortUserResponse
import com.jass.userservice.model.User
import com.jass.userservice.model.UserAccountStatus
import com.jass.userservice.service.controller_service.UsersService
import com.jass.userservice.service.model_service.UserAccountStatusService
import com.jass.userservice.service.model_service.UserService
import com.jass.userservice.feign.ProfileService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest
@AutoConfigureMockMvc
class UsersControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockK
    var userService = mockk<UserService>()

    @MockK
    var userAccountStatusService = mockk<UserAccountStatusService>()

    @MockK
    var profileService = mockk<ProfileService>()

    lateinit var usersService: UsersService

    @BeforeEach
    fun setUp() {

    }

    @Test
    fun `createUser should return CREATED status when user is successfully created`() {
        val createUserRequest = CreateUserRequest("test@example.com")
        val user = User().apply {
            id = 1
            email = "test@example.com"
            status = UserAccountStatus().apply {
                id = 0
                name = "ACTIVE"
            }
        }
        val shortUserResponse = ShortUserResponse().userToShortUserResponse(user)

        var controller = UsersController(
            UsersService(
                userService,
                profileService,
                userAccountStatusService
            )
        )
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

        every { userService.findByEmail(createUserRequest.email) } returns null
        every { userService.create(createUserRequest) } returns user
        every { userAccountStatusService.findById(0) } returns UserAccountStatus().apply { id = 0; name = "ACTIVE" }
        every { profileService.createProfile(createUserRequest.email, user.id) } returns ResponseEntity(null, HttpStatus.CREATED)

        mockMvc.perform(
            post("/api/v1/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"email\": \"test@example.com\" }")
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.email").value("test@example.com"))

        verify { userService.findByEmail(createUserRequest.email) }
        verify { userService.create(createUserRequest) }
        verify { profileService.createProfile(createUserRequest.email, user.id) }
    }
}
