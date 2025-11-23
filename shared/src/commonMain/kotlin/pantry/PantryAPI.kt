package pantry

import common.API
import models.PantryItemDTO

class PantryAPI: API() {

    suspend fun create(groupId: Long, item: PantryItemDTO): Result<PantryItemDTO> {
         print("Creating new item: $item with $groupId")
         return post("/pantry", PantryItemDTO(
             groupId,
             name = item.name,
             quantity = item.quantity,
             category = item.category,
             unit = item.unit,
             minimumQuantity = item.minimumQuantity,
         ))
    }
    suspend fun get(groupId: Long): Result<List<PantryItemDTO>> {
        return get("/pantry", request = mapOf("groupID" to "$groupId", "outOfStock" to "false"))
    }

    suspend fun update(groupId: Long, item: PantryItemDTO): Result<PantryItemDTO> {
        print("Updating item: $item")
        return post("/pantry/update", PantryItemDTO(
            groupId = groupId,
            name = item.name,
            quantity = item.quantity,
            category = item.category,
            unit = item.unit,
            minimumQuantity = item.minimumQuantity,
        ))
    }
}