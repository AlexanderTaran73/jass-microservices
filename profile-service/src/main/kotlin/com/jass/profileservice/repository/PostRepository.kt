package com.jass.profileservice.repository

import com.jass.profileservice.module.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository: JpaRepository<Post, Int> {
}