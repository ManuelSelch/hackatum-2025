package org.example.project.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import models.ErrorResponse
import models.GroupCreateRequest
import models.GroupJoinRequest
import models.GroupListRequest
import models.GroupListResponse
import models.GroupResponse
import org.example.project.db.GroupDao
import org.example.project.db.UserDao
import org.example.project.db.toResponse

fun Route.groupRoutes(userDao: UserDao, groupDao: GroupDao) {
    authenticate("auth-jwt") {
        route("/group") {
            post("/create") {
                val request = call.receive<GroupCreateRequest>();

                val name = request.name.trim()
                val creatorID = request.creatorID

                if (name.isEmpty() || creatorID == 0L) {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        ErrorResponse("Missing required fields: name, creatorID")
                    )
                    return@post
                }

                val existing = userDao.getById(creatorID)
                if (existing == null) {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        ErrorResponse("Invalid creatorID")
                    )
                    return@post
                }

                val group = groupDao.create(name, creatorID)
                call.respond(HttpStatusCode.Created, group.toResponse())
            }

            post("/join") {
                val request = call.receive<GroupJoinRequest>()

                val groupID = request.groupID
                val userID = request.userID

                val success = groupDao.addUser(groupID, userID)
                if (!success) {
                    call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid groupID or userID"))
                    return@post
                }

                call.respond(HttpStatusCode.OK)
            }

            get("/list") {
                val request = call.receive<GroupListRequest>()

                val userID = request.userID

                val groups = groupDao.listUserGroups(userID)
                call.respond(HttpStatusCode.OK, GroupListResponse(groups = groups.map { it.toResponse() }))
            }
        }
    }
}