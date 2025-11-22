package home

import common.API
import models.GroupCreateRequest
import models.GroupListResponse
import models.GroupResponse

class HomeAPI: API() {
    suspend fun create(name: String): Result<GroupResponse> {
        return post("/groups/create", GroupCreateRequest(name))
    }

    suspend fun list(): Result<GroupListResponse> {
        return get("/groups")
    }
}