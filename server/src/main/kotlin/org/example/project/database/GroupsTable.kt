package org.example.project.database

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object GroupsTable : LongIdTable("groups") {
    val name = varchar("name", length = 255).uniqueIndex()
}

object GroupsMembersTable : LongIdTable("group_members") {
    val groupId = reference("group_id", GroupsTable, onDelete = ReferenceOption.CASCADE)
    val userId = reference("user_id", UsersTable, onDelete = ReferenceOption.CASCADE)
}