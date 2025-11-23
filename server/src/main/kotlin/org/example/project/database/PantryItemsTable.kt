package org.example.project.database

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.charLength

object PantryItemsTable : Table("pantry_items") {
    val groupID= reference("group_id", GroupsTable, onDelete = ReferenceOption.CASCADE)
    val name = varchar("name", length = 255).check { it.charLength() greaterEq 3}
    val unit = varchar("unit", length = 255).check { it.charLength() greater 0}
    val quantity = integer("quantity").check { it greaterEq 0 }
    val minimumQuantity = integer("minimum_quantity").check { it greaterEq 0 }
    val category = varchar("category", length = 255)

    override val primaryKey = PrimaryKey(groupID, name)
}