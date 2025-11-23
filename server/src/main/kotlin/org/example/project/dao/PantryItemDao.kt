package org.example.project.dao

import io.ktor.http.HttpStatusCode
import org.example.project.database.PantryItemsTable
import org.example.project.domain.entities.GroupEntity
import org.example.project.domain.models.PantryItem
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

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

    fun update(newItem: PantryItem): Result<PantryItem> = transaction {
        runCatching {
            val updatedRows = PantryItemsTable.update({
                (PantryItemsTable.groupID eq newItem.groupID) and
                (PantryItemsTable.name eq newItem.name)
            }) {
                it[unit] = newItem.unit
                it[quantity] = newItem.quantity
                it[minimumQuantity] = newItem.minimumQuantity
                it[category] = newItem.category
            }

            if (updatedRows > 0) {
                newItem
            } else {
                throw IllegalArgumentException("No matching PantryItem found!")
            }
        }
    }

    fun delete(item: PantryItem): Result<Boolean> = transaction {
        runCatching {
            val nDelted = PantryItemsTable.deleteWhere {
                (PantryItemsTable.groupID eq item.groupID) and
                (PantryItemsTable.name eq item.name)
            }

            if (nDelted > 0) {
                true
            } else {
                throw IllegalArgumentException("No matching PantryItem found!")
            }
        }
    }
}