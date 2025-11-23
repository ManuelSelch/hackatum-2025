package models

import kotlinx.serialization.Serializable
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
    val user: UserDTO
)

// GROUPS

// - /groups/create
// request
@Serializable
data class GroupCreateRequest(
    val name: String
)
// response - see GroupResponse

// - /groups/join
// request
@Serializable
data class GroupJoinRequest(
    val groupID: Long,
)

// response - no data

// response
@Serializable
data class GroupListResponse(
    val groups: List<GroupDTO>
)

// PANTRY
// - /pantry
// request

// response - see PantryItemResponse