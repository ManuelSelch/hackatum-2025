package login

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import models.ErrorResponse
import models.LoginRequest
import models.LoginResponse
import models.RegisterRequest
import models.UserResponse

const val URL = "http://0.0.0.0:8080"

data class ApiException(
    val statusCode: Int,
    val error: ErrorResponse? = null
) : Exception("HTTP $statusCode: ${error?.error}")

class LoginAPI {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun login(username: String, password: String): Result<LoginResponse> {
        return post("/auth/login", LoginRequest(username, password))
    }

    suspend fun register(username: String, password: String): Result<UserResponse> {
        return post("/auth/register", RegisterRequest(username, username, password))
    }

    private suspend inline fun <reified I, reified O> post(endpoint: String, request: I): Result<O> {
        println("POST $endpoint")
        return try {
            val response = client.post(URL + endpoint) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }

            // parse error response
            if (!response.status.isSuccess()) {
                val errorBody = try { response.body<ErrorResponse>() } catch (e: Exception) { null }
                return Result.failure(ApiException(response.status.value, errorBody))
            }

            Result.success(response.body())
        } catch (e: Exception) {
            println("error: $e")
            Result.failure(e)
        }
    }
}
