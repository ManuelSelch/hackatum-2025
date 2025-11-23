package models

import kotlinx.serialization.Serializable

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
    val groupId: Long,
    val name: String,
    val quantity: Int,
    val category: String,
    val unit: String,
    val minimumQuantity: Int,
)

@Serializable
data class PantryItemRequest(
    val groupId: Long,
    val name: String,
    val quantity: Int,
    val category: String,
    val unit: String,
    val minimumQuantity: Int,
)