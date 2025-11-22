package org.example.project.routes

import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import models.ErrorResponse
import models.LoginRequest
import models.LoginResponse
import models.RegisterRequest
import models.UserResponse
import org.example.project.dao.UserDao
import org.example.project.util.JwtService
import org.example.project.domain.models.toResponse

/**
 * Authentication-related routes: register and login.
 */

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
                created.toResponse()
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
                    user = user.toResponse()
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