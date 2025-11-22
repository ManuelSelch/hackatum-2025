package org.example.project.db

import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar

/**
 * Ktorm table mapping for the groups table.
 */
object Groups : Table<Nothing>(tableName = "groups") {
    val id = long("id").primaryKey()
    val name = varchar("name")
    val createdAt = varchar("created_at")
}

/**
 * Ktorm table mapping for the junction table group_members.
 * Composite primary key (group_id, user_id) is enforced in DB schema. 
 */
object GroupMembers : Table<Nothing>(tableName = "group_members") {
    val groupId = long("group_id")
    val userId = long("user_id")
    val createdAt = varchar("created_at")
}
