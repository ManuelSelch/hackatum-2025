package org.example.project.database

import models.ExpenseResponse
import models.PantryItemResponse
import org.example.project.domain.models.User
import org.example.project.domain.models.toResponse
import java.math.BigDecimal



data class Expense(
    val id: Long,
    val groupId: Long,
    val payer: User,
    val amount: BigDecimal,
    val description: String,
    val borrowers: List<User>,
)

fun Expense.toResponse() = ExpenseResponse(
    id = id,
    groupId = groupId,
    payer = payer.toResponse(),
    amount = amount.toDouble(),
    description = description,
    borrowers = borrowers.map { it.toResponse() },
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

fun PantryItem.toResponse() = PantryItemResponse(
    id = id,
    groupId = groupId,
    name = name,
    unit = unit,
    quantity = quantity,
    minimumQuantity = minimumQuantity,
    category = category,
)