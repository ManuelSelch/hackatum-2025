package org.example.project.db

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.sql.Connection

/**
 * Simple SQLite database manager for the server module using Exposed.
 *
 * Responsibilities:
 * - Create or open a SQLite database file.
 * - Ensure the base schema exists (e.g., the users table).
 * - Provide a Ktorm Database instance for queries.
 */
class DatabaseManager private constructor(private val dbPath: String) {
    private val db: Database = Database.connect(dbPath)

    init {
        transaction(db) {
            // Create tables if missing
            SchemaUtils.createMissingTablesAndColumns(
                UsersTable,
                GroupsTable,
                GroupMembersTable,
                ExpensesTable,
                ExpenseBorrowersTable
            )
        }
    }

    /** Expose Exposed Database for DAO usage if needed. */
    fun getDatabase(): Database = db

    companion object {
        /**
         * Create an in-memory SQLite database for testing purposes.
         * The database will be shared across connections and persist for the lifetime of the process.
         */
        fun createTesting(): DatabaseManager {
            val unique = java.util.UUID.randomUUID().toString()
            // Each test application instance gets its own isolated in-memory DB
            return DatabaseManager("jdbc:sqlite:/tmp/testdb-$unique")
        }

        /**
         * Create (or open) a SQLite database at the given path. If parent directories
         * are missing, they will be created. The schema is initialized on first use.
         */
        fun create(dbPath: String): DatabaseManager {
            val path = "jdbc:sqlite:$dbPath"
            ensureParentDir(dbPath)
            return DatabaseManager(path)
        }

        private fun ensureParentDir(dbPath: String) {
            val path = Paths.get(dbPath)
            val parent = path.toAbsolutePath().parent
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent)
            }
        }
    }
}