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


@Transactional
@ExtendWith(value = [TestContainersExtension::class])
class UsersControllerTest: AbstractIntegrationTest() {
//    @MockK
//    var userService = mockk<UserService>()
//
//    @MockK
//    var userAccountStatusService = mockk<UserAccountStatusService>()

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
//
//    @Test
//    fun `createUser user with deleted status`() {
//        val user = User().apply {
//            id = 1
//            email = "test@example.com"
//            status = UserAccountStatus().apply {
//                id = 2
//                name = "DELETED"
//            }
//        }
//        every { userService.findByEmail(any()) } returns user
//        every { userAccountStatusService.findById(0) } returns UserAccountStatus().apply { id = 0; name = "ACTIVE" }
//        every { profileService.createProfile(any(), any()) } returns ResponseEntity(HttpStatus.CREATED)
//
//
//        mockMvc.perform(
//            post("/api/v1/user/create")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{ \"email\": \"test@example.com\" }")
//        )
//            .andExpect(status().isCreated)
//            .andExpect(jsonPath("$.id").value(1))
//            .andExpect(jsonPath("$.email").value("test@example.com"))
//            .andExpect(jsonPath("$.status.name").value("ACTIVE"))
//
//        verify(exactly = 1) { userService.findByEmail(any()) }
//        verify(exactly = 1) { profileService.createProfile(any(), any()) }
//    }
//
//    @Test
//    fun `create user with busy email`() {
//        val user = User().apply {
//            id = 1
//            email = "test@example.com"
//            status = UserAccountStatus().apply {
//                id = 0
//                name = "ACTIVE"
//            }
//        }
//        every { userService.findByEmail(any()) } returns user
//
//        mockMvc.perform(
//            post("/api/v1/user/create")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{ \"email\": \"test@example.com\" }")
//        )
//            .andExpect(status().isConflict)
//    }
}
