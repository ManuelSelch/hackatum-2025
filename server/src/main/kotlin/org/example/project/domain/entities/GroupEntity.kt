package org.example.project.domain.entities

import org.example.project.database.ExpensesTable
import org.example.project.database.GroupsMembersTable
import org.example.project.database.GroupsTable
import org.example.project.domain.models.Group
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class GroupEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<GroupEntity>(GroupsTable)

    var name by GroupsTable.name
    var members by UserEntity via GroupsMembersTable
    val expenses by ExpenseEntity referrersOn ExpensesTable.groupId

    // Convert to business logic model
    fun toModel() = Group(
        id = id.value,
        name = name,
        members = members.toList().map { it.toModel() },
        expenses = expenses.toList().map { it.toModel() }
    )
}