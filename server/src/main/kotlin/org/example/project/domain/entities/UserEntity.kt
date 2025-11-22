package org.example.project.domain.entities

import org.example.project.database.GroupsMembersTable
import org.example.project.database.UsersTable
import org.example.project.domain.models.User
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserEntity>(UsersTable)

    var name by UsersTable.name
    var email by UsersTable.email
    var password by UsersTable.password
    var groups by GroupEntity via GroupsMembersTable

    // Convert to business logic model
    fun toModel() = User(
        id = id.value,
        name = name,
        email = email,
        password = password
    )
}