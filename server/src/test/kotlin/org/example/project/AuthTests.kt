import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import models.*
import org.example.project.module
import kotlin.test.*

class AuthEndpointsTest {
    @Test
    fun `test register creates new user`() = testApplication {
        application { module(true) }
        client = createClient {
            install(ContentNegotiation) { json() }
        }

        val response = client.post("/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(RegisterRequest(
                name = "John Doe",
                email = "john@example.com",
                password = "securePassword123"
            ))
        }

        assertEquals(HttpStatusCode.Created, response.status)
        val user = Json.decodeFromString<UserDTO>(response.bodyAsText())
        assertEquals("John Doe", user.name)
        assertEquals("john@example.com", user.email)
        assertTrue(user.id > 0)
    }

    @Test
    fun `test register with duplicate email fails`() = testApplication {
        application { module(true) }
        client = createClient {
            install(ContentNegotiation) { json() }
        }

        // Register first user
        client.post("/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(RegisterRequest(
                name = "John Doe",
                email = "duplicate@example.com",
                password = "password123"
            ))
        }

        // Try to register with same email
        val response = client.post("/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(RegisterRequest(
                name = "Jane Doe",
                email = "duplicate@example.com",
                password = "password456"
            ))
        }

        assertEquals(HttpStatusCode.Conflict, response.status)
        val error = Json.decodeFromString<ErrorResponse>(response.bodyAsText())
        assertTrue(error.error.contains("email", ignoreCase = true))
    }

    @Test
    fun `test login with valid credentials`() = testApplication {
        application { module(true) }
        client = createClient {
            install(ContentNegotiation) { json() }
        }

        // Register user first
        client.post("/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(RegisterRequest(
                name = "John Doe",
                email = "login@example.com",
                password = "password123"
            ))
        }

        // Login
        val response = client.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(
                email = "login@example.com",
                password = "password123"
            ))
        }

        assertEquals(HttpStatusCode.OK, response.status)
        val loginResponse = Json.decodeFromString<LoginResponse>(response.bodyAsText())
        assertEquals("login@example.com", loginResponse.user.email)
        assertTrue(loginResponse.token.isNotEmpty())
        assertTrue(loginResponse.message.isNotEmpty())
    }

    @Test
    fun `test login with invalid credentials`() = testApplication {
        application { module(true) }
        client = createClient {
            install(ContentNegotiation) { json() }
        }

        val response = client.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(
                email = "nonexistent@example.com",
                password = "wrongpassword"
            ))
        }

        assertEquals(HttpStatusCode.Unauthorized, response.status)
        val error = Json.decodeFromString<ErrorResponse>(response.bodyAsText())
        assertTrue(error.error.isNotEmpty())
    }
}