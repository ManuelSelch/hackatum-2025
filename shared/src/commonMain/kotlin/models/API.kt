package models

import kotlinx.serialization.Serializable

// Request/Response models
@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val createdAt: String? = null
)

@Serializable
data class LoginResponse(
    val message: String,
    val token: String,
    val user: UserResponse
)

@Serializable
data class ErrorResponse(
    val error: String
)