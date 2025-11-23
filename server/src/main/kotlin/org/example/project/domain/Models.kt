package org.example.project.database

import models.ExpenseDTO
import models.PantryItemDTO
import org.example.project.domain.models.User
import org.example.project.domain.models.toDTO
import java.math.BigDecimal



data class Expense(
    val id: Long,
    val groupId: Long,
    val payer: User,
    val amount: BigDecimal,
    val description: String,
    val borrowers: List<User>,
)

fun Expense.toResponse() = ExpenseDTO(
    id = id,
    groupId = groupId,
    payer = payer.toDTO(),
    amount = amount.toDouble(),
    description = description,
    borrowers = borrowers.map { it.toDTO() },
)

data class PantryItem(
    val id: Long,
    val groupId: Long,
    val name: String,
    val unit: String,
    val quantity: Int,
    val minimumQuantity: Int,
    val category: String
)

fun PantryItem.toResponse() = PantryItemDTO(
    groupId = groupId,
    name = name,
    unit = unit,
    quantity = quantity,
    minimumQuantity = minimumQuantity,
    category = category,
)