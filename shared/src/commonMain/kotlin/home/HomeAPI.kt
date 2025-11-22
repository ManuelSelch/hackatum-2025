package home

import common.API
import models.GroupCreateRequest
import models.GroupResponse

class HomeAPI: API() {
    suspend fun create(name: String): Result<GroupResponse> {
        return post("/group/create", GroupCreateRequest(name, 0))
    }
}