package org.example.project.routes

import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import models.PantryItemDTO
import models.PantryItemsCategorizedResponse
import org.example.project.dao.PantryItemDao
import org.example.project.domain.models.PantryItem
import org.example.project.domain.models.toDTO
import org.example.project.services.PantryItemService

fun Route.pantryRoutes(pantryDao: PantryItemDao) {
    authenticate("auth-jwt") {
        route("/pantry") {
            post("") {
                val request = call.receive<PantryItemDTO>();

                if (request.groupId == 0L || request.name.isBlank() || request.unit.isBlank() || request.quantity <= 0 || request.minimumQuantity <= 0 || request.category.isBlank())
                    return@post call.respond(HttpStatusCode.BadRequest, "Missing required fields: groupId, name, unit, quantity, minimumQuantity, category")

                if (pantryDao.getPantryItem(request.groupId, request.name) != null)
                    return@post call.respond(HttpStatusCode.BadRequest, "Item already exists in the pantry")

                val item = pantryDao.create(
                    request.groupId,
                    request.name,
                    request.unit,
                    request.quantity,
                    request.minimumQuantity,
                    request.category
                )

                call.respond(HttpStatusCode.OK, item.toDTO())
            }

            get(""){
                val groupID = call.parameters["groupID"]?.toLongOrNull() ?: 0L

                if (groupID == 0L) {
                    call.respond(HttpStatusCode.BadRequest, "groupID is required")
                    return@get
                }

                val outOfStock = call.parameters["outOfStock"]?.toBooleanStrictOrNull() ?: false
                val items = if (outOfStock) {
                    pantryDao.getPantryItemsByGroupID(groupID)
                } else {
                    PantryItemService(pantryDao).getPantryItemsOutOfStock(groupID)
                }

                items
                    .onFailure { call.respond(HttpStatusCode.BadRequest, it.message?: "Unknown error") }
                    .onSuccess { call.respond(HttpStatusCode.OK, it.map { item -> item.toDTO()}) }
            }

            get("/categorized") {
                val groupID = call.parameters["groupID"]?.toLongOrNull() ?: 0L

                if (groupID == 0L) {
                    call.respond(HttpStatusCode.BadRequest, "groupID is required")
                    return@get
                }

                PantryItemService(pantryDao).getPantryItemsGroupedByCategory(groupID)
                    .onFailure { call.respond(HttpStatusCode.BadRequest, it.message?: "Unknown error") }
                    .onSuccess { call.respond(HttpStatusCode.OK, PantryItemsCategorizedResponse(items = it.mapValues { (_, value) -> value.map { item -> item.toDTO()} })) }
            }

            post("/update") {
                val request = call.receive<PantryItemDTO>()

                val result = pantryDao.update(
                    PantryItem(
                        groupID = request.groupId,
                        name = request.name,
                        unit = request.unit,
                        quantity = request.quantity,
                        minimumQuantity = request.minimumQuantity,
                        category = request.category
                    )
                )

                result
                    .onFailure { call.respond(HttpStatusCode.BadRequest, it.message?: "Unknown error") }
                    .onSuccess { call.respond(HttpStatusCode.OK, it.toDTO()) }
            }

            post("/delete") {
                val request = call.receive<PantryItemDTO>()

                val result = pantryDao.delete(
                    PantryItem(
                        groupID = request.groupId,
                        name = request.name,
                        unit = request.unit,
                        quantity = request.quantity,
                        minimumQuantity = request.minimumQuantity,
                        category = request.category
                    )
                )

                result
                    .onFailure { call.respond(HttpStatusCode.BadRequest, it.message?: "Unknown error") }
                    .onSuccess { call.respond(HttpStatusCode.OK) }
            }
        }
    }
}