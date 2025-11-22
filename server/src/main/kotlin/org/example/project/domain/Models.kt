package org.example.project.db

import models.UserResponse

/**
 * Business logic models
 */
data class User(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
    val createdAt: String?
)

fun User.toResponse() = UserResponse(
    id = id,
    name = name,
    email = email,
    createdAt = createdAt
)

data class Group(
    val id: Long,
    val name: String,
    val members: List<User>,
    val createdAt: String?
)

fun Group.toResponse() = models.GroupResponse(
    id = id,
    name = name,
    members = members.map { it.toResponse() },
    createdAt = createdAt
)