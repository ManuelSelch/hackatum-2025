package login

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import models.LoginRequest
import models.LoginResponse
import models.RegisterRequest
import models.UserResponse

const val URL = "http://0.0.0.0:8080"

class LoginAPI {
    private val client = HttpClient()

    suspend fun login(username: String, password: String): Result<LoginResponse> {
        return post("/auth/login", LoginRequest(username, password))
    }

    suspend fun register(username: String, password: String): Result<UserResponse> {
        return post("/auth/register", RegisterRequest(username, username, password))
    }

    private suspend inline fun <reified I, reified O> post(endpoint: String, request: I): Result<O> {
        println("POST $endpoint")
        return try {
            val response: O = client.post(URL + endpoint) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
            println("success: $response")
            Result.success(response)
        } catch (e: Exception) {
            println("error: $e")
            Result.failure(e)
        }
    }
}
