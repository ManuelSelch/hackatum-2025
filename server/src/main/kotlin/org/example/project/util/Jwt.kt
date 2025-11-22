package org.example.project.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.respondText
import java.time.Instant
import java.util.Date

/**
 * Configuration values used for JWT auth.
 */
data class JwtConfig(
    val secret: String,
    val issuer: String,
    val audience: String,
    val realm: String = "Access to 'auth-jwt'",
    val ttlSeconds: Long = 3600,
)

class JwtService(private val cfg: JwtConfig) {
    private val algorithm: Algorithm = Algorithm.HMAC256(cfg.secret)

    fun generateToken(
        userId: Long,
        email: String,
        name: String
    ): String {
        val now = Instant.now()
        val expires = now.plusSeconds(cfg.ttlSeconds)
        return JWT.create()
            .withIssuer(cfg.issuer)
            .withAudience(cfg.audience)
            .withClaim("uid", userId)
            .withClaim("email", email)
            .withClaim("name", name)
            .withIssuedAt(Date.from(now))
            .withExpiresAt(Date.from(expires))
            .sign(algorithm)
    }

    fun verifier() = JWT
        .require(algorithm)
        .withIssuer(cfg.issuer)
        .withAudience(cfg.audience)
        .build()
}

fun Application.installJwt(cfg: JwtConfig) {
    val algorithm = Algorithm.HMAC256(cfg.secret)
    authentication {
        jwt("auth-jwt") {
            realm = cfg.realm
            verifier(
                JWT
                    .require(algorithm)
                    .withIssuer(cfg.issuer)
                    .withAudience(cfg.audience)
                    .build()
            )
            validate { credential ->
                // Basic validation: require subject or our uid claim
                val uid = credential.payload.getClaim("uid")
                if (!uid.isMissing) JWTPrincipal(credential.payload) else null
            }
            challenge { _, _ ->
                call.respondText(
                    text = "Unauthorized",
                    status = io.ktor.http.HttpStatusCode.Unauthorized
                )
            }
        }
    }
}
