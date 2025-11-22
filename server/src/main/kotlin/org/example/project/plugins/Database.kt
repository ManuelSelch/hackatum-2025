package org.example.project.plugins

import io.ktor.server.application.Application
import org.example.project.database.ExpenseBorrowersTable
import org.example.project.database.ExpensesTable
import org.example.project.database.GroupsMembersTable
import org.example.project.database.GroupsTable
import org.example.project.database.PantryItemsTable
import org.example.project.database.UsersTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabase() {
    val dbUrl = environment.config.property("ktor.database.url").getString()
    val database = Database.connect(dbUrl)

    transaction {
        SchemaUtils.createMissingTablesAndColumns(
            UsersTable,
            GroupsTable,
            GroupsMembersTable,
            ExpensesTable,
            ExpenseBorrowersTable,
            PantryItemsTable
        )
    }
}

fun Application.configureTestDatabase() {
    val testDBUrl = environment.config.property("ktor.database.testUrl").getString()
    val testDatabase = Database.connect(testDBUrl)

    transaction {
        SchemaUtils.createMissingTablesAndColumns(
            UsersTable,
            GroupsTable,
            GroupsMembersTable,
            ExpensesTable,
            ExpenseBorrowersTable,
            PantryItemsTable
        )
    }
}