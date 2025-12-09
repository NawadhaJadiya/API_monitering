package com.example.serviceMoniter.meta.controller
import com.example.serviceMoniter.meta.model.User
import com.example.serviceMoniter.meta.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val user: User? = null
)

@RestController
@RequestMapping("/api/auth")
class LoginController(private val userService: UserService) {

    @GetMapping("/login")
    fun hello(): String {
        return "Hello, anyone!"
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        val user = userService.authenticateUser(loginRequest.email, loginRequest.password)
        
        return if (user != null) {
            ResponseEntity.ok(
                LoginResponse(
                    success = true,
                    message = "Login successful",
                    user = user
                )
            )
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                LoginResponse(
                    success = false,
                    message = "Invalid email or password"
                )
            )
        }
    }
}
