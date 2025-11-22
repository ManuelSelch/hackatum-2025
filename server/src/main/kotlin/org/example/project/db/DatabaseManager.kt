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

    private val jdbcUrl: String = "jdbc:sqlite:$dbPath"

    private val db: Database = Database.connect(jdbcUrl)

    init {
        transaction(db) {
            // Create tables if missing
            SchemaUtils.createMissingTablesAndColumns(UsersTable, GroupsTable, GroupMembersTable)
        }
    }

    /** Expose Exposed Database for DAO usage if needed. */
    fun getDatabase(): Database = db

    companion object {
        /**
         * Create (or open) a SQLite database at the given path. If parent directories
         * are missing, they will be created. The schema is initialized on first use.
         */
        @JvmStatic
        fun create(dbPath: String): DatabaseManager {
            ensureParentDir(dbPath)
            return DatabaseManager(dbPath)
        }

        private fun ensureParentDir(dbPath: String) {
            val path: Path = Paths.get(dbPath)
            val parent = path.toAbsolutePath().parent
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent)
            }
        }
    }
}