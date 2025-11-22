package org.example.project.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.example.project.db.DbGroup
import org.example.project.db.GroupDao
import org.example.project.db.UserDao

@Serializable
data class CreateGroupRequest(val name: String)

@Serializable
data class GroupResponse(
    val id: Long,
    val name: String,
    val createdAt: String? = null
)

@Serializable
data class GroupWithMembersResponse(
    val id: Long,
    val name: String,
    val members: List<MemberResponse>,
    val createdAt: String? = null
)

@Serializable
data class MemberResponse(
    val id: Long,
    val name: String,
    val email: String,
    val createdAt: String? = null
)

@Serializable
data class AddMemberRequest(val userId: Long)

@Serializable
data class ErrorBody(val error: String)

fun Route.groupRoutes(groups: GroupDao, users: UserDao) {
    authenticate("auth-jwt") {
        route("/groups") {
            // Create group
            post {
                val req = runCatching { call.receive<CreateGroupRequest>() }.getOrElse {
                    call.respond(HttpStatusCode.BadRequest, ErrorBody("Invalid JSON body"))
                    return@post
                }
                val name = req.name.trim()
                if (name.isEmpty()) {
                    call.respond(HttpStatusCode.BadRequest, ErrorBody("Group name is required"))
                    return@post
                }
                try {
                    val created = groups.create(name)
                    call.respond(HttpStatusCode.Created, created.toResponse())
                } catch (e: Exception) {
                    // Likely UNIQUE constraint violation
                    call.respond(HttpStatusCode.Conflict, ErrorBody("Group name already exists"))
                }
            }

            // List groups (pagination)
            get {
                val limit = call.request.queryParameters["limit"]?.toIntOrNull()?.coerceIn(1, 500) ?: 100
                val offset = call.request.queryParameters["offset"]?.toIntOrNull()?.coerceAtLeast(0) ?: 0
                val list = groups.list(limit, offset).map { it.toResponse() }
                call.respond(list)
            }

            // Group by id with members
            get("/{id}") {
                val id = call.parameters["id"]?.toLongOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, ErrorBody("Invalid group id"))
                    return@get
                }
                val pair = groups.getByIdWithMembers(id)
                if (pair == null) {
                    call.respond(HttpStatusCode.NotFound, ErrorBody("Group not found"))
                    return@get
                }
                val (grp, members) = pair
                call.respond(
                    GroupWithMembersResponse(
                        id = grp.id,
                        name = grp.name,
                        members = members.map { m ->
                            MemberResponse(
                                id = m.id,
                                name = m.name,
                                email = m.email,
                                createdAt = m.createdAt
                            )
                        },
                        createdAt = grp.createdAt
                    )
                )
            }

            // Delete group
            delete("/{id}") {
                val id = call.parameters["id"]?.toLongOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, ErrorBody("Invalid group id"))
                    return@delete
                }
                val existed = groups.deleteById(id)
                if (!existed) {
                    call.respond(HttpStatusCode.NotFound, ErrorBody("Group not found"))
                } else {
                    call.respond(HttpStatusCode.NoContent)
                }
            }

            // List members of a group
            get("/{id}/members") {
                val id = call.parameters["id"]?.toLongOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, ErrorBody("Invalid group id"))
                    return@get
                }
                val group = groups.getById(id)
                if (group == null) {
                    call.respond(HttpStatusCode.NotFound, ErrorBody("Group not found"))
                    return@get
                }
                val limit = call.request.queryParameters["limit"]?.toIntOrNull()?.coerceIn(1, 500) ?: 100
                val offset = call.request.queryParameters["offset"]?.toIntOrNull()?.coerceAtLeast(0) ?: 0
                val members = groups.getMembers(id, limit, offset).map { m ->
                    MemberResponse(id = m.id, name = m.name, email = m.email, createdAt = m.createdAt)
                }
                call.respond(members)
            }

            // Add member to a group
            post("/{id}/members") {
                val id = call.parameters["id"]?.toLongOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, ErrorBody("Invalid group id"))
                    return@post
                }
                val req = runCatching { call.receive<AddMemberRequest>() }.getOrElse {
                    call.respond(HttpStatusCode.BadRequest, ErrorBody("Invalid JSON body"))
                    return@post
                }
                val group = groups.getById(id)
                if (group == null) {
                    call.respond(HttpStatusCode.NotFound, ErrorBody("Group not found"))
                    return@post
                }
                val user = users.getById(req.userId)
                if (user == null) {
                    call.respond(HttpStatusCode.NotFound, ErrorBody("User not found"))
                    return@post
                }
                val inserted = groups.addUser(id, req.userId)
                if (inserted) {
                    call.respond(HttpStatusCode.Created)
                } else {
                    // Already a member
                    call.respond(HttpStatusCode.OK)
                }
            }

            // Remove member from a group
            delete("/{id}/members/{userId}") {
                val id = call.parameters["id"]?.toLongOrNull()
                val userId = call.parameters["userId"]?.toLongOrNull()
                if (id == null || userId == null) {
                    call.respond(HttpStatusCode.BadRequest, ErrorBody("Invalid group id or user id"))
                    return@delete
                }
                val group = groups.getById(id)
                if (group == null) {
                    call.respond(HttpStatusCode.NotFound, ErrorBody("Group not found"))
                    return@delete
                }
                val removed = groups.removeUser(id, userId)
                if (removed) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, ErrorBody("Membership not found"))
                }
            }
        }
    }
}

private fun DbGroup.toResponse() = GroupResponse(
    id = id,
    name = name,
    createdAt = createdAt
)
