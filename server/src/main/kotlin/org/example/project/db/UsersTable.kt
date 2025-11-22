package org.example.project.db

import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar

/**
 * Ktorm table mapping for the users table.
 */
object Users : Table<Nothing>(tableName = "users") {
    val id = long("id").primaryKey()
    val name = varchar("name")
    val email = varchar("email")
    val password = varchar("password")
    // Store created_at as text in SQLite, map as String for simplicity.
    val createdAt = varchar("created_at")
}
