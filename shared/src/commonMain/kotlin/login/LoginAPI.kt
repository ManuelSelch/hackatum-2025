package login

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val username: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val token: String,
    val userId: String
)

const val URL = "http://localhost:80"

class LoginAPI {
    private val client = HttpClient()

    suspend fun login(username: String, password: String): Result<LoginResponse> {
        return post("/users/login", LoginRequest(username, password))
    }

    suspend fun register(username: String, password: String): Result<LoginResponse> {
        return post("/users/register", LoginRequest(username, password))
    }

    private suspend inline fun <reified I, reified O> post(endpoint: String, request: I): Result<O> {
        try {
            val res: O = client.post(URL + endpoint) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()

            return Result.success(res)
        } catch(e: Exception) {
            return Result.failure(e)
        }
    }
}
