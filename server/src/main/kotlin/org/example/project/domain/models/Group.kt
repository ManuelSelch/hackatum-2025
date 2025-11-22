package org.example.project.domain.models

import org.example.project.database.Expense
import org.example.project.database.PantryItem
import org.example.project.database.toResponse

data class Group(
    val id: Long,
    val name: String,
    val members: List<User>,
    val expenses: List<Expense>,
    //val pantryItems: List<PantryItem>,
)

fun Group.toResponse() = models.GroupResponse(
    id = id,
    name = name,
    members = members.map { it.toResponse() },
    expenses = expenses.map { it.toResponse() }
    //pantryItems = pantryItems.map { it.toResponse() },
)