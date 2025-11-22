package org.example.project.domain.entities

import org.example.project.database.Expense
import org.example.project.database.ExpenseBorrowersTable
import org.example.project.database.ExpensesTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ExpenseEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ExpenseEntity>(ExpensesTable)

    var groupId by ExpensesTable.groupId
    var payer by UserEntity referencedOn ExpensesTable.payerID
    var amount by ExpensesTable.amount
    var description by ExpensesTable.description
    var borrowers by UserEntity via ExpenseBorrowersTable

    // Convert to business logic model
    fun toModel() = Expense(
        id = id.value,
        groupId = groupId.value,
        payer = payer.toModel(),
        amount = amount,
        description = description,
        borrowers = borrowers.toList().map { it.toModel() }
    )
}