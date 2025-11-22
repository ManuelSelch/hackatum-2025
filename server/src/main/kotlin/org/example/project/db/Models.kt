package org.example.project.db

import kotlinx.serialization.Serializable

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