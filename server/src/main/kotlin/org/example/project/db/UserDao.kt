package org.example.project.db

import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.sequenceOf

/**
 * Ktorm-based DAO providing CRUD operations for users table.
 */
class UserDao(private val dbManager: DatabaseManager) {

    private val database get() = dbManager.getDatabase()

    /** Create a new user. Returns the created DbUser with generated id. */
    fun create(name: String, email: String, password: String): DbUser {
        val user = DbUser {
            this.name = name
            this.email = email
            this.password = password
        }
        database.user
        database.sequenceOf(DbUsers).add(user)
        return user;
    }

    /** Retrieve a user by id, or null if not found. */
    fun getById(id: Long): DbUser? {
        return database
            .from(Users)
            .select(Users.id, Users.name, Users.email, Users.password, Users.createdAt)
            .where { Users.id eq id }
            .limit(1)
            .map { row ->
                DbUser(
                    id = row[Users.id]!!,
                    name = row[Users.name]!!,
                    email = row[Users.email]!!,
                    password = row[Users.password]!!,
                    createdAt = row[Users.createdAt]
                )
            }
            .firstOrNull()
    }

    /** Retrieve a user by unique email, or null if not found. */
    fun getByEmail(email: String): DbUser? {
        return database
            .from(Users)
            .select(Users.id, Users.name, Users.email, Users.password, Users.createdAt)
            .where { Users.email eq email }
            .limit(1)
            .map { row ->
                DbUser(
                    id = row[Users.id]!!,
                    name = row[Users.name]!!,
                    email = row[Users.email]!!,
                    password = row[Users.password]!!,
                    createdAt = row[Users.createdAt]
                )
            }
            .firstOrNull()
    }

    /** List users with pagination. */
    fun list(limit: Int = 100, offset: Int = 0): List<DbUser> {
        return database
            .from(Users)
            .select(Users.id, Users.name, Users.email, Users.password, Users.createdAt)
            .orderBy(Users.id.asc())
            .limit(limit, offset)
            .map { row ->
                DbUser(
                    id = row[Users.id]!!,
                    name = row[Users.name]!!,
                    email = row[Users.email]!!,
                    password = row[Users.password]!!,
                    createdAt = row[Users.createdAt]
                )
            }
    }

    /** Update a user by id. Returns true if a row was updated. */
    fun updateById(id: Long, user: User): Boolean {
        val affected = database.update(Users) {
            set(Users.name, user.name)
            set(Users.email, user.email)
            set(Users.password, user.password)
            where { Users.id eq id }
        }
        return affected > 0
    }

    /** Delete a user by id. Returns true if a row was deleted. */
    fun deleteById(id: Long): Boolean {
        val affected = database.delete(Users) { it.id eq id }
        return affected > 0
    }
}
