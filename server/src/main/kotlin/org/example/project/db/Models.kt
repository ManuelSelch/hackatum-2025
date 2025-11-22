package org.example.project.db

import kotlinx.serialization.Serializable

/**
 * Database models for the server.
 *
 * Note: This project currently doesn't include a persistence/ORM library.
 * The models below are plain Kotlin data classes and can be adapted later
 * for a specific database framework (e.g., Exposed, Hibernate, Ktorm, etc.).
 */

/**
 * Standard user model.
 *
 * @property name The display name of the user.
 * @property email The user's unique email address.
 * @property password The user's password (store only a secure hash in production).
 */
@Serializable
data class User(
    val name: String,
    val email: String,
    val password: String,
)

/**
 * Representation of a user row as stored in the database.
 * Includes the generated primary key id and createdAt timestamp.
 */
data class DbUser(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
    val createdAt: String?,
)

/**
 * Business logic group model with members populated.
 *
 * @property id The group's unique identifier.
 * @property name The name of the group.
 * @property members The list of users who are members of this group.
 * @property createdAt The timestamp when the group was created.
 */
@Serializable
data class Group(
    val id: Long,
    val name: String,
    val members: List<User>,
    val createdAt: String?,
)

/**
 * Representation of a group row as stored in the database.
 * Includes the generated primary key id and createdAt timestamp.
 * The members are stored separately in the GroupMember junction table.
 */
data class DbGroup(
    val id: Long,
    val name: String,
    val createdAt: String?,
)