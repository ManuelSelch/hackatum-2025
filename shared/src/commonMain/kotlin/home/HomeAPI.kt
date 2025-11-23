package home

import common.API
import models.GroupCreateRequest
import models.GroupJoinRequest
import models.GroupListResponse
import models.GroupDTO
import models.UserDTO

class HomeAPI: API() {
    suspend fun create(name: String): Result<GroupDTO> {
        return post("/groups/create", GroupCreateRequest(name))
    }

    suspend fun list(): Result<GroupListResponse> {
        return get("/groups")
    }

    suspend fun join(groupId: Long): Result<GroupDTO> {
        return post("/groups/join", GroupJoinRequest(groupId))
    }
}