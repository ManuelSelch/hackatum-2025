package org.example.project.database

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.charLength

object UsersTable : LongIdTable("users") {
    val name = varchar("name", length = 255).check { it.charLength() greater 3 }
    val email = varchar("email", length = 255).uniqueIndex() //.check { it.match("^.+@.+\\..+\$") }
    val password = varchar("password", length = 255).check { it.charLength() greater 3 }
}