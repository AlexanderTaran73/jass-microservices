package com.jass.userservice.controller

import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.lifecycle.Startables

//TODO: move to other place
class TestContainersExtension: BeforeAllCallback {
    override fun beforeAll(p0: ExtensionContext?) {
        println("TestContainersExtension init")
        System.setProperty("DB_URL", postgresql.getJdbcUrl())
        System.setProperty("DB_USERNAME", postgresql.username)
        System.setProperty("DB_PASSWORD", postgresql.password)
    }

    companion object{
        private val postgresql = PostgreSQLContainer("postgres:latest").withReuse(true)

        init {
            postgresql.portBindings = listOf("5432:5433")
            Startables.deepStart(postgresql).join()

        }
    }
}