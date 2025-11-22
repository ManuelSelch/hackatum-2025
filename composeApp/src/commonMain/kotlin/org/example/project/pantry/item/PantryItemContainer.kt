package org.example.project.pantry.item

data class PantryItem(
    val name: String,
    val unit: String?,
    val quantity: Double,
    val category: String?,
    val minimumQuantity: Int?
)
