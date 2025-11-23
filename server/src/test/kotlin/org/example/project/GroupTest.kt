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

class GroupCreateEndpointTest {

    @Test
    fun `test create group with valid data`() = testApplication {
        application { module(true) }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        // Register and login
        client.post("/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(RegisterRequest(
                name = "Test User",
                email = "testuser@example.com",
                password = "password123"
            ))
        }

        val loginResponse = client.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(
                email = "testuser@example.com",
                password = "password123"
            ))
        }
        val loginData = Json.decodeFromString<LoginResponse>(loginResponse.bodyAsText())

        // Create group
        val response = client.post("/group/create") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer ${loginData.token}")
            setBody(GroupCreateRequest(
                name = "My Awesome Group"
            ))
        }

        assertEquals(HttpStatusCode.Created, response.status)
        val group = Json.decodeFromString<GroupDTO>(response.bodyAsText())
        assertEquals("My Awesome Group", group.name)
        assertTrue(group.id > 0)
        assertEquals(group.members.size, 1)
        assertEquals(group.members[0].id, loginData.user.id)
        assertTrue(group.expenses.isEmpty())
    }

    @Test
    fun `test create group without authentication`() = testApplication {
        application { module(true) }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val response = client.post("/group/create") {
            contentType(ContentType.Application.Json)
            setBody(GroupCreateRequest(
                name = "Unauthorized Group"
            ))
        }

        assertEquals(HttpStatusCode.Unauthorized, response.status)
    }

    @Test
    fun `test create group with invalid JWT token`() = testApplication {
        application { module(true) }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val response = client.post("/group/create") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer invalid.token.here")
            setBody(GroupCreateRequest(
                name = "Test Group"
            ))
        }

        assertEquals(HttpStatusCode.Unauthorized, response.status)
    }

    @Test
    fun `test create group with empty name`() = testApplication {
        application { module(true) }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        // Register and login
        client.post("/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(RegisterRequest(
                name = "Test User",
                email = "emptyname@example.com",
                password = "password123"
            ))
        }

        val loginResponse = client.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(
                email = "emptyname@example.com",
                password = "password123"
            ))
        }
        val loginData = Json.decodeFromString<LoginResponse>(loginResponse.bodyAsText())

        // Try to create group with empty name
        val response = client.post("/group/create") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer ${loginData.token}")
            setBody(GroupCreateRequest(
                name = ""
            ))
        }

        assertEquals(HttpStatusCode.BadRequest, response.status)
        val error = Json.decodeFromString<ErrorResponse>(response.bodyAsText())
        assertTrue(error.error.contains("Missing required fields: name"))
    }

    @Test
    fun `test create group with whitespace-only name`() = testApplication {
        application { module(true) }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        // Register and login
        client.post("/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(RegisterRequest(
                name = "Test User",
                email = "whitespace@example.com",
                password = "password123"
            ))
        }

        val loginResponse = client.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(
                email = "whitespace@example.com",
                password = "password123"
            ))
        }
        val loginData = Json.decodeFromString<LoginResponse>(loginResponse.bodyAsText())

        // Try to create group with whitespace-only name
        val response = client.post("/group/create") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer ${loginData.token}")
            setBody(GroupCreateRequest(
                name = "   "
            ))
        }

        assertEquals(HttpStatusCode.BadRequest, response.status)
        val error = Json.decodeFromString<ErrorResponse>(response.bodyAsText())
        assertTrue(error.error.contains("Missing required fields: name"))
    }

    @Test
    fun `test create group trims whitespace from name`() = testApplication {
        application { module(true) }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        // Register and login
        client.post("/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(RegisterRequest(
                name = "Test User",
                email = "trimtest@example.com",
                password = "password123"
            ))
        }

        val loginResponse = client.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(
                email = "trimtest@example.com",
                password = "password123"
            ))
        }
        val loginData = Json.decodeFromString<LoginResponse>(loginResponse.bodyAsText())

        // Create group with whitespace around name
        val response = client.post("/group/create") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer ${loginData.token}")
            setBody(GroupCreateRequest(
                name = "  Trimmed Group  "
            ))
        }

        assertEquals(HttpStatusCode.Created, response.status)
        val group = Json.decodeFromString<GroupDTO>(response.bodyAsText())
        assertEquals("Trimmed Group", group.name)
    }

    @Test
    fun `test create multiple groups with same user`() = testApplication {
        application { module(true) }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        // Register and login
        client.post("/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(RegisterRequest(
                name = "Test User",
                email = "multigroup@example.com",
                password = "password123"
            ))
        }

        val loginResponse = client.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(
                email = "multigroup@example.com",
                password = "password123"
            ))
        }
        val loginData = Json.decodeFromString<LoginResponse>(loginResponse.bodyAsText())

        // Create first group
        val response1 = client.post("/group/create") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer ${loginData.token}")
            setBody(GroupCreateRequest(
                name = "Group 1"
            ))
        }

        assertEquals(HttpStatusCode.Created, response1.status)
        val group1 = Json.decodeFromString<GroupDTO>(response1.bodyAsText())

        // Create second group
        val response2 = client.post("/group/create") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer ${loginData.token}")
            setBody(GroupCreateRequest(
                name = "Group 2"
            ))
        }

        assertEquals(HttpStatusCode.Created, response2.status)
        val group2 = Json.decodeFromString<GroupDTO>(response2.bodyAsText())

        // Verify they are different groups
        assertNotEquals(group1.id, group2.id)
        assertEquals("Group 1", group1.name)
        assertEquals("Group 2", group2.name)
    }

    @Test
    fun `test create group with special characters in name`() = testApplication {
        application { module(true) }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        // Register and login
        client.post("/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(RegisterRequest(
                name = "Test User",
                email = "special@example.com",
                password = "password123"
            ))
        }

        val loginResponse = client.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(
                email = "special@example.com",
                password = "password123"
            ))
        }
        val loginData = Json.decodeFromString<LoginResponse>(loginResponse.bodyAsText())

        // Create group with special characters
        val response = client.post("/group/create") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer ${loginData.token}")
            setBody(GroupCreateRequest(
                name = "Group & Friends! 🎉"
            ))
        }

        assertEquals(HttpStatusCode.Created, response.status)
        val group = Json.decodeFromString<GroupDTO>(response.bodyAsText())
        assertEquals("Group & Friends! 🎉", group.name)
    }

    @Test
    fun `test create group returns creator as member`() = testApplication {
        application { module(true) }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        // Register and login
        client.post("/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(RegisterRequest(
                name = "Test User",
                email = "creator@example.com",
                password = "password123"
            ))
        }

        val loginResponse = client.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(
                email = "creator@example.com",
                password = "password123"
            ))
        }
        val loginData = Json.decodeFromString<LoginResponse>(loginResponse.bodyAsText())

        // Create group
        val response = client.post("/group/create") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer ${loginData.token}")
            setBody(GroupCreateRequest(
                name = "New Group",
            ))
        }

        assertEquals(HttpStatusCode.Created, response.status)
        val group = Json.decodeFromString<GroupDTO>(response.bodyAsText())

        // Verify creator is in members list
        assertEquals(1, group.members.size)
        assertEquals(loginData.user.id, group.members[0].id)
    }

    @Test
    fun `test create group returns empty expenses list`() = testApplication {
        application { module(true) }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        // Register and login
        client.post("/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(RegisterRequest(
                name = "Test User",
                email = "expenses@example.com",
                password = "password123"
            ))
        }

        val loginResponse = client.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(
                email = "expenses@example.com",
                password = "password123"
            ))
        }
        val loginData = Json.decodeFromString<LoginResponse>(loginResponse.bodyAsText())

        // Create group
        val response = client.post("/group/create") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer ${loginData.token}")
            setBody(GroupCreateRequest(
                name = "New Group"
            ))
        }

        assertEquals(HttpStatusCode.Created, response.status)
        val group = Json.decodeFromString<GroupDTO>(response.bodyAsText())

        // Verify expenses list is empty for new group
        assertTrue(group.expenses.isEmpty())
    }
}
