package org.example.project.domain.models

data class Expense(
    val id: Long,
    val groupId: Long,
    val payer: User,
    val amount: Double,
    val description: String,
    val borrowers: List<User>,
)

fun Expense.toDTO() = models.ExpenseDTO(
    id = id,
    groupId = groupId,
    payer = payer.toDTO(),
    amount = amount,
    description = description,
    borrowers = borrowers.map { it.toDTO() }
)