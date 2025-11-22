package org.example.project

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import org.example.project.auth.JwtConfig
import org.example.project.auth.JwtService
import org.example.project.auth.installJwt
import org.example.project.db.DatabaseManager
import org.example.project.db.GroupDao
import org.example.project.db.UserDao
import org.example.project.routes.authRoutes
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.routes.groupRoutes

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val db = DatabaseManager.create("data/app.db")
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
        authRoutes(users, jwt)
        groupRoutes(users, groups)
    }
}