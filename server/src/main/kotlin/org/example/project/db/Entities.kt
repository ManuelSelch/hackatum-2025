package org.example.project.db

import models.Group
import models.User
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
    var createdAt by UsersTable.createdAt

    // Convert to business logic model
    fun toModel() = User(
        id = id.value,
        name = name,
        email = email,
        password = password,
        createdAt = createdAt
    )
}

class GroupEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<GroupEntity>(GroupsTable)

    var name by GroupsTable.name
    var members by UserEntity via GroupMembersTable
    var createdAt by GroupsTable.createdAt

    // Convert to business logic model
    fun toModel(): Group {
        return Group(
            id = id.value,
            name = name,
            members = members.toList().map { it.toModel() },
            createdAt = createdAt
        )
    }
}