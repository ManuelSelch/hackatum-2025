package pantry

import common.API
import models.PantryItemDTO

class PantryAPI: API() {

    suspend fun create(groupId: Long, item: PantryItem): Result<PantryItemDTO> {
         return post("/pantry", PantryItemDTO(
             groupId,
             name = item.name,
             quantity = item.quantity.toInt(),
             category = item.category.toString(),
             unit = item.unit?: "",
             minimumQuantity = item.minimumQuantity?: 0,
         ))
    }
    /*
    suspend fun update(): Result<PantryItemResponse> {
        return post("/pantry", PantryItemUpdateRequest())
    }
     */
}