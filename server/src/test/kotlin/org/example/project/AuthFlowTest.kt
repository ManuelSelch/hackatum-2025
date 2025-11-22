package org.example.project

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.io.path.createTempFile
import kotlin.io.path.deleteIfExists
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AuthFlowTest {

    @Test
    fun register_login_me_flow() = testApplication {
        // Use a temporary isolated SQLite DB file for this test run
        val dbFile = createTempFile(prefix = "test-db-", suffix = ".sqlite").toFile()
        try {
            System.setProperty("db.path", dbFile.absolutePath)

            application { module() }

            // 1) Register
            val email = "testuser@example.com"
            val registerResponse = client.post("/auth/register") {
                contentType(ContentType.Application.Json)
                setBody(
                    """
                    {
                      "name": "Test User",
                      "email": "$email",
                      "password": "secret"
                    }
                    """.trimIndent()
                )
            }
            assertEquals(HttpStatusCode.Created, registerResponse.status, "Expected 201 on register")
            val registerBody = registerResponse.bodyAsText()
            assertTrue(registerBody.contains("\"email\":\"$email\""), "Register body should contain email")

            // 2) Login
            val loginResponse = client.post("/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(
                    """
                    {
                      "email": "$email",
                      "password": "secret"
                    }
                    """.trimIndent()
                )
            }
            assertEquals(HttpStatusCode.OK, loginResponse.status, "Expected 200 on login")
            val loginBody = loginResponse.bodyAsText()
            // Extract token with a simple regex: "token":"..."
            val token = Regex(""""token"\s*:\s*"([^"]+)"""")
                .find(loginBody)
                ?.groupValues?.getOrNull(1)
            assertTrue(!token.isNullOrBlank(), "Login response should include a token, body=$loginBody")

            // 3) Call /auth/me with Bearer token
            val meResponse = client.get("/auth/me") {
                header(HttpHeaders.Authorization, "Bearer $token")
            }
            assertEquals(HttpStatusCode.OK, meResponse.status, "Expected 200 on /auth/me")
            val meBody = meResponse.bodyAsText()
            assertTrue(meBody.contains("\"email\":\"$email\""), "Me body should contain email")
        } finally {
            // Clean up
            runCatching { System.clearProperty("db.path") }
            runCatching { dbFile.toPath().deleteIfExists() }
        }
    }
}
