package pantry

import common.API
import models.PantryItemCreateRequest
import models.PantryItemResponse

class PantryAPI: API() {

    suspend fun create(groupId: Long, item: PantryItem): Result<PantryItemResponse> {
         return post("/pantry", PantryItemCreateRequest(
             1,
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