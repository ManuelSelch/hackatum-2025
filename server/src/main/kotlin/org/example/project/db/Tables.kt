package org.example.project.db

import org.jetbrains.exposed.dao.id.LongIdTable

/**
 * Exposed table mappings using IdTable for DAO pattern.
 */
object UsersTable : LongIdTable("users") {
    val name = varchar("name", length = 255)
    val email = varchar("email", length = 255).uniqueIndex()
    val password = varchar("password", length = 255)
    val createdAt = varchar("created_at", length = 255).nullable()
}

object GroupsTable : LongIdTable("groups") {
    val name = varchar("name", length = 255).uniqueIndex()
    val createdAt = varchar("created_at", length = 255).nullable()
}

object GroupMembersTable : LongIdTable("group_members") {
    val groupId = reference("group_id", GroupsTable, onDelete = org.jetbrains.exposed.sql.ReferenceOption.CASCADE)
    val userId = reference("user_id", UsersTable, onDelete = org.jetbrains.exposed.sql.ReferenceOption.CASCADE)
    val createdAt = varchar("created_at", length = 255).nullable()
}
