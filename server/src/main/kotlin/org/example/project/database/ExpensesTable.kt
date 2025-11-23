package org.example.project.database

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object ExpensesTable : LongIdTable("expenses") {
    val groupID = reference("group_id", GroupsTable, onDelete = ReferenceOption.CASCADE)
    val payerID = reference("payer_id", UsersTable, onDelete = ReferenceOption.CASCADE)
    val amount = decimal("amount", precision = 10, scale = 2)
    val description = varchar("description", length = 500)
}

object ExpenseBorrowersTable : LongIdTable("expense_borrowers") {
    val expenseId = reference("expense_id", ExpensesTable, onDelete = ReferenceOption.CASCADE)
    val userId = reference("user_id", UsersTable, onDelete = ReferenceOption.CASCADE)
}