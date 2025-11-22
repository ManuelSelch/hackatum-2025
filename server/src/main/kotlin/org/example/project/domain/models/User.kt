package org.example.project.domain.models

import models.UserResponse

data class User(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
)

fun User.toResponse() = UserResponse(
    id = id,
    name = name,
    email = email,
)