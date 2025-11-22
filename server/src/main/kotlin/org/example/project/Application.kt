package org.example.project

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*
import org.example.project.auth.JwtConfig
import org.example.project.auth.JwtService
import org.example.project.auth.installJwt
import org.example.project.db.DatabaseManager
import org.example.project.db.GroupDao
import org.example.project.db.UserDao
import org.example.project.routes.authRoutes
import org.example.project.routes.groupRoutes
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val dbPath = "data/app.db"
    val db = DatabaseManager.create(dbPath)
    val users = UserDao(db)
    val groups = GroupDao(db)

    // Install JSON content negotiation (accept and produce application/json)
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            encodeDefaults = false
        })
    }

    // JWT configuration from environment variables with safe defaults for development
    val jwtCfg = JwtConfig(
        secret = System.getenv("JWT_SECRET") ?: "dev-secret-change-me",
        issuer = System.getenv("JWT_ISSUER") ?: "hackatum-2025-server",
        audience = System.getenv("JWT_AUDIENCE") ?: "hackatum-2025-clients",
        realm = System.getenv("JWT_REALM") ?: "Access to 'auth-jwt'",
        ttlSeconds = (System.getenv("JWT_TTL_SECONDS")?.toLongOrNull() ?: 3600L)
    )
    installJwt(jwtCfg)
    val jwt = JwtService(jwtCfg)

    routing {
        // Swagger UI hosting and OpenAPI doc exposure
        // Use generated OpenAPI endpoint as source for Swagger UI in tests & prod
        //openAPI(path = "/openapi")
        //swaggerUI(path = "/swagger", apiUrl = "/", api = "openapi.json")

        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }

        // Auth endpoints: /auth/register and /auth/login
        authRoutes(users, jwt)

        // Group endpoints (JWT-protected): /groups, /groups/{id}, membership management
        groupRoutes(groups, users)
    }
}