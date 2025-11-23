package org.example.project.domain.models

import models.UserDTO

data class User(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
)

fun User.toDTO() = UserDTO(
    id = id,
    name = name,
    email = email,
)