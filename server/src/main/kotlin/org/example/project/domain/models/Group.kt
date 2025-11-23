package org.example.project.domain.models

data class Group(
    val id: Long,
    val name: String,
    val members: List<User>
)

fun Group.toDTO() = models.GroupDTO(
    id = id,
    name = name,
    members = members.map { it.toDTO() }
)