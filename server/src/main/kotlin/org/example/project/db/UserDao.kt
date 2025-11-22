package org.example.project.db
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * DAO providing CRUD operations for users using Exposed entities.
 */
class UserDao(private val dbManager: DatabaseManager) {

    private val database get() = dbManager.getDatabase()

    /** Create a new user. Returns the created User with generated id. */
    fun create(name: String, email: String, password: String): User {
        return transaction(database) {
            UserEntity.new {
                this.name = name
                this.email = email
                this.password = password
                this.createdAt = System.currentTimeMillis().toString()
            }.toModel()
        }
    }

    /** Retrieve a user by id, or null if not found. */
    fun getById(id: Long): User? = transaction(database) {
        UserEntity.findById(id)?.toModel()
    }

    /** Retrieve a user by unique email, or null if not found. */
    fun getByEmail(email: String): User? = transaction(database) {
        UserEntity.find { UsersTable.email eq email }
            .limit(1)
            .firstOrNull()
            ?.toModel()
    }

    /** List users with pagination. */
    fun list(limit: Int = 100, offset: Long = 0): List<User> = transaction(database) {
        UserEntity.all()
            .orderBy(UsersTable.id to SortOrder.ASC)
            .limit(limit, offset)
            .map { it.toModel() }
    }

    /** Update a user by id. Returns the updated user or null if not found. */
    fun updateById(id: Long, name: String, email: String, password: String): User? = transaction(database) {
        UserEntity.findById(id)?.apply {
            this.name = name
            this.email = email
            this.password = password
        }?.toModel()
    }

    /** Delete a user by id. Returns true if a row was deleted. */
    fun deleteById(id: Long): Boolean = transaction(database) {
        val entity = UserEntity.findById(id)
        if (entity != null) {
            entity.delete()
            true
        } else {
            false
        }
    }
}