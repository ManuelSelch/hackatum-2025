package models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDTO(
    val error: String
)

@Serializable
data class UserDTO (
    val id: Long,
    val name: String,
    val email: String,
)

@Serializable
data class GroupDTO(
    val id: Long,
    val name: String,
    val members: List<UserDTO>,
)

@Serializable
data class ExpenseDTO(
    val id: Long,
    val groupId: Long,
    val payer: UserDTO,
    val amount: Double,
    val description: String,
    val borrowers: List<UserDTO>
)

@Serializable
data class PantryItemDTO(
    val groupId: Long,
    var name: String,
    val quantity: Int,
    var category: String,
    var unit: String,
    val minimumQuantity: Int,
)