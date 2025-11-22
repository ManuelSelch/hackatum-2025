package models

import kotlinx.serialization.Serializable

// General
@Serializable
data class ErrorResponse(
    val error: String
)

@Serializable
data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val createdAt: String? = null
)

@Serializable
data class GroupResponse(
    val id: Long,
    val name: String,
    val members: List<UserResponse>,
    val createdAt: String? = null
)

// AUTH

// - /auth/register
// request
@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)
// response - see UserResponse

// - /auth/login
// request
@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)
// response
@Serializable
data class LoginResponse(
    val message: String,
    val token: String,
    val user: UserResponse
)

// GROUPS

// - /groups/create
// request
@Serializable
data class GroupCreateRequest(
    val name: String,
    val creatorID: Long
)
// response - see GroupResponse

// - /groups/join
// request
@Serializable
data class GroupJoinRequest(
    val groupID: Long,
    val userID: Long
)

// response - no data

// - /groups/list
// request
@Serializable
data class GroupListRequest(
    val userID: Long
)
// response
@Serializable
data class GroupListResponse(
    val groups: List<GroupResponse>
)