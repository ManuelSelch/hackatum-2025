package login

import common.API
import models.LoginRequest
import models.LoginResponse
import models.RegisterRequest
import models.UserResponse

class LoginAPI: API() {
    suspend fun login(username: String, password: String): Result<LoginResponse> {
        val res: Result<LoginResponse> = post("/auth/login", LoginRequest(username, password))

        // update JWT token+
        token = res.getOrNull()?.token ?: ""

        return res;
    }

    suspend fun register(username: String, password: String): Result<UserResponse> {
        return post("/auth/register", RegisterRequest(username, username, password))
    }
}
