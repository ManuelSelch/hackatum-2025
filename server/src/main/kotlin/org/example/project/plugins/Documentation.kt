package org.example.project.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.openapi.openAPI
import io.ktor.server.routing.*

fun Application.configureDocumentation() {
    val path = environment.config.property("ktor.documentation.path").getString()
    val swaggerFile = environment.config.property("ktor.documentation.swaggerFile").getString()
    routing {
        openAPI(
            path = path,
            swaggerFile = swaggerFile
        )
    }
}