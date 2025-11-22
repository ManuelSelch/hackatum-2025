package org.example.project

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import org.example.project.util.JwtConfig
import org.example.project.util.JwtService
import org.example.project.util.installJwt
import org.example.project.routes.authRoutes
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.dao.GroupDao
import org.example.project.dao.UserDao
import org.example.project.plugins.configureDatabase
import org.example.project.plugins.configureSerialization
import org.example.project.plugins.configureTestDatabase
import org.example.project.routes.groupRoutes

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    if (testing) configureTestDatabase() else configureDatabase()
    configureSerialization()

    val jwtCfg = JwtConfig(
        secret = environment.config.property("jwt.secret").getString(),
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        realm = environment.config.property("jwt.realm").getString(),
        ttlSeconds = environment.config.property("jwt.ttlSeconds").getString().toLong(),
    )
    installJwt(jwtCfg)
    val jwt = JwtService(jwtCfg)

    // init daos
    val users = UserDao()
    val groups = GroupDao()

    routing {
        authRoutes(users, jwt)
        groupRoutes(users, groups)
    }
}
