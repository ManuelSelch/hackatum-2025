package org.example.project.db

import org.ktorm.entity.Entity

/**
 * Representation of a user row as stored in the database.
 * Includes the generated primary key id and createdAt timestamp.
 */
interface DbUser : Entity<DbUser> {
    companion object : Entity.Factory<DbUser>()

    val id: Long
    var name: String
    var email: String
    var password: String
    var createdAt: String?
}

/**
 * Representation of a group row as stored in the database.
 * Includes the generated primary key id and createdAt timestamp.
 * The members are stored separately in the GroupMember junction table.
 */
interface DbGroup : Entity<DbGroup> {
    companion object : Entity.Factory<DbGroup>()

    val id: Long
    var name: String
    var createdAt: String?
}

/**
 * Representation of a group member row in the junction table.
 * Composite primary key (group_id, user_id) is enforced in DB schema.
 */

interface DbGroupMember : Entity<DbGroupMember> {
    companion object : Entity.Factory<DbGroupMember>()

    var groupId: Long
    var userId: Long
    var createdAt: String?
}