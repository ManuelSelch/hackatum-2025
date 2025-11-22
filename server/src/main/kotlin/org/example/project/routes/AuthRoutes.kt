package org.example.project.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.example.project.auth.JwtService
import org.example.project.db.UserDao

/**
 * Authentication-related routes: register and login.
 */

// Request/Response models
@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val createdAt: String? = null
)

@Serializable
data class LoginResponse(
    val message: String,
    val token: String,
    val user: UserResponse
)

@Serializable
data class ErrorResponse(
    val error: String
)

fun Route.authRoutes(userDao: UserDao, jwt: JwtService) {
    route("/auth") {
        post("/register") {
            val request = call.receive<RegisterRequest>()

            // Validate input
            val name = request.name.trim()
            val email = request.email.trim().lowercase()
            val password = request.password

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    ErrorResponse("Missing required fields: name, email, password")
                )
                return@post
            }

            // Check for existing user by email
            val existing = userDao.getByEmail(email)
            if (existing != null) {
                call.respond(
                    HttpStatusCode.Conflict,
                    ErrorResponse("Email already registered")
                )
                return@post
            }

            // Create user (Note: store password hashes in real applications)
            val created = userDao.create(name, email, password)

            call.respond(
                HttpStatusCode.Created,
                UserResponse(
                    id = created.id,
                    name = created.name,
                    email = created.email,
                    createdAt = created.createdAt
                )
            )
        }

        post("/login") {
            val request = call.receive<LoginRequest>()

            val email = request.email.trim().lowercase()
            val password = request.password

            if (email.isEmpty() || password.isEmpty()) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    ErrorResponse("Missing required fields: email, password")
                )
                return@post
            }

            val user = userDao.getByEmail(email)
            if (user == null || user.password != password) {
                call.respond(
                    HttpStatusCode.Unauthorized,
                    ErrorResponse("Invalid email or password")
                )
                return@post
            }

            // Issue JWT token
            val token = jwt.generateToken(userId = user.id, email = user.email, name = user.name)

            call.respond(
                LoginResponse(
                    message = "Login successful",
                    token = token,
                    user = UserResponse(
                        id = user.id,
                        name = user.name,
                        email = user.email
                    )
                )
            )
        }

        // Example protected endpoint to retrieve current principal claims
        authenticate("auth-jwt") {
            get("/me") {
                val principal = call.principal<JWTPrincipal>()
                val id = principal?.getClaim("uid", Long::class) ?: 0L
                val name = principal?.payload?.getClaim("name")?.asString() ?: ""
                val email = principal?.payload?.getClaim("email")?.asString() ?: ""

                call.respond(
                    UserResponse(
                        id = id,
                        name = name,
                        email = email
                    )
                )
            }
        }
    }
}