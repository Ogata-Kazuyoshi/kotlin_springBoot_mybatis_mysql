package com.example.springBoot_mybatis.routes

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class Auth {
    @GetMapping("/authentification")
    fun index ():String {
        return "CheckAuthApi"
    }
}