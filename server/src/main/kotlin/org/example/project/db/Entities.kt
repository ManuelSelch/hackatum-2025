package org.example.project.db

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * Exposed DAO entities for database operations.
 */
class UserEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserEntity>(UsersTable)

    var name by UsersTable.name
    var email by UsersTable.email
    var password by UsersTable.password
    var groups by GroupEntity via GroupMembersTable

    // Convert to business logic model
    fun toModel() = User(
        id = id.value,
        name = name,
        email = email,
        password = password
    )
}

class GroupEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<GroupEntity>(GroupsTable)

    var name by GroupsTable.name
    var members by UserEntity via GroupMembersTable
    val expenses by ExpenseEntity referrersOn ExpensesTable.groupId

    // Convert to business logic model
    fun toModel() = Group(
        id = id.value,
        name = name,
        members = members.toList().map { it.toModel() },
        expenses = expenses.toList().map { it.toModel() }
    )
}

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
