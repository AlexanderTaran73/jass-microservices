package com.jass.profileservice.controller

import com.jass.profileservice.service.controller_service.FriendsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/profile/friends")
class FriendsController(
    private val friendsService: FriendsService
) {

    @PostMapping("/send_request")
    fun sendRequest(@RequestHeader("User-Email") email: String, @RequestParam id: Int): ResponseEntity<HttpStatus> {
        return friendsService.sendRequest(email, id)
    }

    @PatchMapping("/accept_request")
    fun acceptRequest(@RequestHeader("User-Email") email: String, @RequestParam id: Int): ResponseEntity<HttpStatus> {
        return friendsService.acceptRequest(email, id)
    }

    @PatchMapping("/reject_request")
    fun rejectRequest(@RequestHeader("User-Email") email: String, @RequestParam id: Int): ResponseEntity<HttpStatus> {
        return friendsService.rejectRequest(email, id)
    }

    @DeleteMapping("/cancel_request")
    fun cancelRequest(@RequestHeader("User-Email") email: String, @RequestParam id: Int): ResponseEntity<HttpStatus> {
        return friendsService.cancelRequest(email, id)
    }

    @DeleteMapping("/delete_from_friends")
    fun deleteFromFriends(@RequestHeader("User-Email") email: String, @RequestParam id: Int): ResponseEntity<HttpStatus> {
        return friendsService.deleteFromFriends(email, id)
    }

}