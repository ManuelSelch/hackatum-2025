package login

import common.API
import models.LoginRequest
import models.LoginResponse
import models.RegisterRequest
import models.UserDTO

class LoginAPI: API() {
    private var userId: Long? = null;

    suspend fun login(email: String, password: String): Result<LoginResponse> {
        val res: Result<LoginResponse> = post("/auth/login", LoginRequest(email, password))

        // update JWT token+
        token = res.getOrNull()?.token ?: ""
        userId = res.getOrNull()?.user?.id

        return res;
    }

    suspend fun register(username: String, email: String, password: String): Result<UserDTO> {
        return post("/auth/register", RegisterRequest(username, email, password))
    }

    suspend fun update(username: String, email: String): Boolean {
        if(userId == null) return false
        return postAndForget("/auth/update", UserDTO(userId!!,username,email))
    }
}
