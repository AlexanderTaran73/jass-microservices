package com.jass.userservice.controller

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

//TODO: move to other place
@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
abstract class AbstractIntegrationTest {

    @Autowired
    lateinit var mockMvc: MockMvc


    @TestConfiguration
    class MockMvcContextPathConfig {

        @Bean
        fun mockMvc(context: WebApplicationContext): MockMvc {
            return MockMvcBuilders
                .webAppContextSetup(context)
                .defaultRequest<DefaultMockMvcBuilder>(
                    get("/").contextPath("/api"),
                ).build()
        }
    }
}