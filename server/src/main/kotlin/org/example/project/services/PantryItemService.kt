package org.example.project.services

import org.example.project.dao.PantryItemDao
import org.example.project.domain.models.PantryItem

class PantryItemService(pantryItemDao: PantryItemDao) {
    val dao = pantryItemDao

    fun getPantryItemsOutOfStock(groupID: Long): Result<List<PantryItem>> = runCatching {
            val items = dao.getPantryItemsByGroupID(groupID).getOrThrow()

            items.filter { it.quantity <= it.minimumQuantity }
        }
    }
}