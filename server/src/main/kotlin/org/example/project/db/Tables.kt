package org.example.project.db

import org.jetbrains.exposed.dao.id.LongIdTable

/**
 * Exposed table mappings using IdTable for DAO pattern.
 */
object UsersTable : LongIdTable("users") {
    val name = varchar("name", length = 255)
    val email = varchar("email", length = 255).uniqueIndex()
    val password = varchar("password", length = 255)
}

object GroupsTable : LongIdTable("groups") {
    val name = varchar("name", length = 255).uniqueIndex()
}

object GroupMembersTable : LongIdTable("group_members") {
    val groupId = reference("group_id", GroupsTable, onDelete = org.jetbrains.exposed.sql.ReferenceOption.CASCADE)
    val userId = reference("user_id", UsersTable, onDelete = org.jetbrains.exposed.sql.ReferenceOption.CASCADE)
}

object ExpensesTable : LongIdTable("expenses") {
    val groupId = reference("group_id", GroupsTable, onDelete = org.jetbrains.exposed.sql.ReferenceOption.CASCADE)
    val payerID = reference("payer_id", UsersTable, onDelete = org.jetbrains.exposed.sql.ReferenceOption.CASCADE)
    val amount = decimal("amount", precision = 10, scale = 2)
    val description = varchar("description", length = 500)
}

object ExpenseBorrowersTable : LongIdTable("expense_borrowers") {
    val expenseId = reference("expense_id", ExpensesTable, onDelete = org.jetbrains.exposed.sql.ReferenceOption.CASCADE)
    val userId = reference("user_id", UsersTable, onDelete = org.jetbrains.exposed.sql.ReferenceOption.CASCADE)
}

object PantryItem : LongIdTable("pantry_items") {
    // name
    // amount
    // unit
    // groupId
    // category
}
