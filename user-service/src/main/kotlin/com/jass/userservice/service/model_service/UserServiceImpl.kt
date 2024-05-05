package com.jass.userservice.service.model_service

import com.jass.userservice.dto.CreateUserRequest
import com.jass.userservice.model.User
import com.jass.userservice.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
@Transactional
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userRoleService: UserRoleService
): UserService {

    override fun create(createUserRequest: CreateUserRequest): User {
        val user = User().also {
            it.email = createUserRequest.email
            it.password = createUserRequest.password
            it.createdAt = LocalDateTime.now()

            val userRole = userRoleService.findByName("ROLE_USER")!!
            it.roles.add(userRole)
        }
        save(user)
        return user
    }

    override fun save(user: User) {
        userRepository.save(user)
    }

    override fun delete(user: User){
        userRepository.delete(user)
    }

    override fun findAll(): List<User> {
        return userRepository.findAll()
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override fun findById(id: Int): User? {
        return userRepository.findById(id).get()
    }
}