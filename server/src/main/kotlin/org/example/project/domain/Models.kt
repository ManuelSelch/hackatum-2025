package org.example.project.db

import models.ExpenseResponse
import models.UserResponse
import java.math.BigDecimal

/**
 * Business logic models
 */
data class User(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
)

fun User.toResponse() = UserResponse(
    id = id,
    name = name,
    email = email,
)

data class Group(
    val id: Long,
    val name: String,
    val members: List<User>,
    val expenses: List<Expense>,
)

fun Group.toResponse() = models.GroupResponse(
    id = id,
    name = name,
    members = members.map { it.toResponse() },
    expenses = expenses.map { it.toResponse() },
)

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