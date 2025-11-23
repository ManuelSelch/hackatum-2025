package org.example.project.dao

import io.ktor.http.HttpStatusCode
import org.example.project.database.PantryItemsTable
import org.example.project.domain.entities.GroupEntity
import org.example.project.domain.models.PantryItem
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class PantryItemDao() {
    fun create(groupID: Long, name: String, unit: String, quantity: Int, minimumQuantity: Int, category: String): PantryItem = transaction {
        PantryItemsTable.insert {
            it[this.groupID] = groupID
            it[this.name] = name
            it[this.unit] = unit
            it[this.quantity] = quantity
            it[this.minimumQuantity] = minimumQuantity
            it[this.category] = category
        }

        PantryItem(
            groupID = groupID,
            name = name,
            unit = unit,
            quantity = quantity,
            minimumQuantity = minimumQuantity,
            category = category,
        )
    }

    fun getPantryItemsByGroupID(groupID: Long): Result<List<PantryItem>> = transaction {
        runCatching {
            GroupEntity.findById(groupID) ?: throw IllegalArgumentException("GroupID is invalid. No matching Group found!")

            PantryItemsTable.selectAll().where { PantryItemsTable.groupID eq groupID }.map {
                PantryItem(
                    groupID = it[PantryItemsTable.groupID].value,
                    name = it[PantryItemsTable.name],
                    unit = it[PantryItemsTable.unit],
                    quantity = it[PantryItemsTable.quantity],
                    minimumQuantity = it[PantryItemsTable.minimumQuantity],
                    category = it[PantryItemsTable.category]
                )
            }
        }
    }
}