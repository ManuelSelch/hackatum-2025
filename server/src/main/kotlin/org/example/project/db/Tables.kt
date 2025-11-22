package org.example.project.db

import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar

/**
 * Ktorm table mapping for the users table.
 */
object DbUsers : Table<DbUser>("users") {
    val id = long("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val email = varchar("email").bindTo { it.email }
    val password = varchar("password").bindTo { it.password }
    val createdAt = varchar("created_at").bindTo { it.createdAt }
}

/**
 * Ktorm table mapping for the groups table.
 */
object Groups : Table<DbGroup>(tableName = "groups") {
    val id = long("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val createdAt = varchar("created_at").bindTo { it.createdAt }
}

/**
 * Ktorm table mapping for the junction table group_members.
 * Composite primary key (group_id, user_id) is enforced in DB schema.
 */
object GroupMembers : Table<DbGroupMember>(tableName = "group_members") {
    val groupId = long("group_id").bindTo { it.groupId }
    val userId = long("user_id").bindTo { it.userId }
    val createdAt = varchar("created_at").bindTo { it.createdAt }
}