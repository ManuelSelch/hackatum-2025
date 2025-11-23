package org.example.project.domain.models

import models.PantryItemResponse

data class PantryItem(
    val groupID: Long,
    val name: String,
    val unit: String,
    val quantity: Int,
    val minimumQuantity: Int,
    val category: String,
)

fun PantryItem.toResponse() = PantryItemResponse(
    groupId = groupID,
    name = name,
    quantity = quantity,
    category = category,
    unit = unit,
    minimumQuantity = minimumQuantity,
)