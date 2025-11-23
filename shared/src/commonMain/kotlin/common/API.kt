package common

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import models.ErrorResponse

const val URL = "http://0.0.0.0:8080"

data class ApiException(
    val statusCode: Int,
    val error: ErrorResponse? = null
) : Exception("HTTP $statusCode: ${error?.error}")

open class API {
    companion object {
        var token: String = ""
    }

     val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    fun clearToken() {
        token = ""
    }

    suspend inline fun <reified I, reified O> post(endpoint: String, request: I): Result<O> {
        return try {
            println("POST $endpoint with token $token")

            val response = client.post(URL + endpoint) {
                header("Authorization", "Bearer $token")
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

    suspend inline fun <reified O> get(endpoint: String, request: Map<String, String>? = null): Result<O> {
        return try {
            println("GET $endpoint with token $token")

            val response = client.get(URL + endpoint) {
                header("Authorization", "Bearer $token")
                contentType(ContentType.Application.Json)
                url {
                    request?.forEach { (key, value) ->
                        parameters.append(key, value)
                    }
                }
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
