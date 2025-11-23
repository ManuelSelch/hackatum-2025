package org.example.project.domain.models

import models.PantryItemDTO

data class PantryItem(
    val groupID: Long,
    val name: String,
    val unit: String,
    val quantity: Int,
    val minimumQuantity: Int,
    val category: String,
)

fun PantryItem.toDTO() = PantryItemDTO(
    groupId = groupID,
    name = name,
    quantity = quantity,
    category = category,
    unit = unit,
    minimumQuantity = minimumQuantity,
)