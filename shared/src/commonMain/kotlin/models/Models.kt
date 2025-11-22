package models

import kotlinx.serialization.Serializable

/**
 * Business logic data classes shared with frontend.
 * These are serializable and contain no database logic.
 */
@Serializable
data class User(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
    val createdAt: String?
)

@Serializable
data class Group(
    val id: Long,
    val name: String,
    val members: List<User>,
    val createdAt: String?
)