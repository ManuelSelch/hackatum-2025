
FROM gradle:8.14-jdk21 AS build

WORKDIR /app

# Copy root project files
COPY build.gradle.kts ./build.gradle.kts
COPY settings.gradle.kts ./settings.gradle.kts
COPY gradle.properties ./gradle.properties

# Copy shared module (required dependency)
COPY shared ./shared

# Copy server module
COPY server/build.gradle.kts ./server/build.gradle.kts
COPY server/src ./server/src

# Download dependencies (cached layer)
RUN gradle :server:dependencies --no-daemon

# Build the application
RUN gradle :server:buildFatJar --no-daemon

# Runtime stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the built JAR from build stage
COPY --from=build /app/server/build/libs/*-all.jar app.jar

# Expose the default Ktor port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]