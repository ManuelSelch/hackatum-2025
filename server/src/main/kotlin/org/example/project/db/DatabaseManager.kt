package org.example.project.db

import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Simple SQLite database manager for the server module using Ktorm.
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
        // Initialize schema on creation to ensure required tables exist.
        initializeSchema()
    }

    /** Expose Ktorm Database for DAO usage. */
    fun getDatabase(): Database = db

    private fun initializeSchema() {
        db.useConnection { conn ->
            conn.createStatement().use { stmt ->
                // Ensure foreign keys are enforced (SQLite requires PRAGMA per connection)
                stmt.execute("PRAGMA foreign_keys = ON;")

                // Users table
                stmt.execute(
                    """
                    CREATE TABLE IF NOT EXISTS users (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        email TEXT NOT NULL UNIQUE,
                        password TEXT NOT NULL,
                        created_at TEXT DEFAULT (datetime('now'))
                    );
                    """.trimIndent()
                )

                // Groups table
                stmt.execute(
                    """
                    CREATE TABLE IF NOT EXISTS groups (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL UNIQUE,
                        created_at TEXT DEFAULT (datetime('now'))
                    );
                    """.trimIndent()
                )

                // Junction table: group_members
                stmt.execute(
                    """
                    CREATE TABLE IF NOT EXISTS group_members (
                        group_id INTEGER NOT NULL,
                        user_id INTEGER NOT NULL,
                        created_at TEXT DEFAULT (datetime('now')),
                        PRIMARY KEY (group_id, user_id),
                        FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE,
                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
                    );
                    """.trimIndent()
                )
            }
        }
    }

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