package org.example.project.domain.models

data class Expense(
    val id: Long,
    val groupId: Long,
    val payer: User,
    val amount: Double,
    val description: String,
    val borrowers: List<User>,
)

fun Expense.toResponse() = models.ExpenseResponse(
    id = id,
    groupId = groupId,
    payer = payer.toResponse(),
    amount = amount,
    description = description,
    borrowers = borrowers.map { it.toResponse() }

)