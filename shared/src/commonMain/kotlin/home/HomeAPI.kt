package home

import common.API
import models.GroupCreateRequest
import models.GroupListRequest
import models.GroupListResponse
import models.GroupResponse

class HomeAPI: API() {
    suspend fun create(name: String): Result<GroupResponse> {
        return post("/group/create", GroupCreateRequest(name, 1))
    }

    suspend fun list(): Result<GroupListResponse> {
        return get("/group/list", GroupListRequest(1))
    }
}