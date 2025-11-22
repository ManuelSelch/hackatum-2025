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
)

@Serializable
data class GroupResponse(
    val id: Long,
    val name: String,
    val members: List<UserResponse>,
    val expenses: List<ExpenseResponse>,
    //val pantryItems: List<PantryItemResponse>
)

@Serializable
data class ExpenseResponse(
    val id: Long,
    val groupId: Long,
    val payer: UserResponse,
    val amount: Double,
    val description: String,
    val borrowers: List<UserResponse>
)

@Serializable
data class PantryItemResponse(
    val id: Long,
    val groupId: Long,
    val name: String,
    val quantity: Int,
    val category: String,
    val unit: String,
    val minimumQuantity: Int,
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
    val groups: List<GroupResponse>
)