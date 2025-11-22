plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    kotlin("plugin.serialization") version "2.1.0"  // Add serialization plugin
    application
}

group = "org.example.project"
version = "1.0.0"
application {
    mainClass.set("org.example.project.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

// Ensure Java 17 toolchain for server module
kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.serverCore)
    implementation(libs.ktor.serverNetty)

    // Ktor ContentNegotiation + JSON (kotlinx.serialization)
    implementation("io.ktor:ktor-server-content-negotiation-jvm:3.3.1")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:3.3.1")

    // Ktor authentication + JWT
    implementation("io.ktor:ktor-server-auth-jvm:3.3.1")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:3.3.1")

    // SQLite JDBC driver for simple file-based database access
    implementation("org.xerial:sqlite-jdbc:3.46.1.0")

    // Ktorm ORM for Kotlin + SQLite support
    implementation("org.ktorm:ktorm-core:3.6.0")
    implementation("org.ktorm:ktorm-support-sqlite:3.6.0")

    // Ktor OpenAPI + Swagger UI (Option A)
    implementation("io.ktor:ktor-server-openapi-jvm:3.3.1")
    implementation("io.ktor:ktor-server-swagger-jvm:3.3.1")

    // Testing
    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)
    testImplementation("io.ktor:ktor-client-content-negotiation:3.3.1")
}