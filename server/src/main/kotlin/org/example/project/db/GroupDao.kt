package org.example.project.db

import org.ktorm.dsl.*

/**
 * DAO for groups and group membership using Ktorm + SQLite.
 * Uses a junction table (group_members) to relate users to groups.
 */
class GroupDao(private val dbManager: DatabaseManager) {

    private val database get() = dbManager.getDatabase()

    // region Groups CRUD

    fun create(name: String): DbGroup {
        val id = database.insertAndGenerateKey(Groups) {
            set(Groups.name, name)
        } as Long
        return getById(id) ?: throw IllegalStateException("Inserted group not found by id=$id")
    }

    fun getById(id: Long): DbGroup? {
        return database
            .from(Groups)
            .select(Groups.id, Groups.name, Groups.createdAt)
            .where { Groups.id eq id }
            .limit(1)
            .map { row ->
                DbGroup(
                    id = row[Groups.id]!!,
                    name = row[Groups.name]!!,
                    createdAt = row[Groups.createdAt]
                )
            }
            .firstOrNull()
    }

    fun list(limit: Int = 100, offset: Int = 0): List<DbGroup> {
        return database
            .from(Groups)
            .select(Groups.id, Groups.name, Groups.createdAt)
            .orderBy(Groups.id.asc())
            .limit(limit, offset)
            .map { row ->
                DbGroup(
                    id = row[Groups.id]!!,
                    name = row[Groups.name]!!,
                    createdAt = row[Groups.createdAt]
                )
            }
    }

    fun deleteById(id: Long): Boolean {
        // ON DELETE CASCADE in schema removes memberships
        val affected = database.delete(Groups) { it.id eq id }
        return affected > 0
    }

    // endregion

    // region Membership management

    /**
     * Add a user to a group. Returns true if a new membership row was inserted.
     * If the membership already exists, returns false (idempotent).
     */
    fun addUser(groupId: Long, userId: Long): Boolean {
        // Use SQLite-specific INSERT OR IGNORE to make this idempotent.
        var inserted = false
        database.useConnection { conn ->
            conn.prepareStatement("INSERT OR IGNORE INTO group_members(group_id, user_id) VALUES (?, ?)").use { ps ->
                ps.setLong(1, groupId)
                ps.setLong(2, userId)
                inserted = ps.executeUpdate() > 0
            }
        }
        return inserted
    }

    /** Remove a user from a group. Returns true if a row was deleted. */
    fun removeUser(groupId: Long, userId: Long): Boolean {
        val affected = database.delete(GroupMembers) { (it.groupId eq groupId) and (it.userId eq userId) }
        return affected > 0
    }

    /** List DbUser members of a group. */
    fun getMembers(groupId: Long, limit: Int = 100, offset: Int = 0): List<DbUser> {
        return database
            .from(GroupMembers)
            .leftJoin(Users, on = GroupMembers.userId eq Users.id)
            .select(Users.id, Users.name, Users.email, Users.password, Users.createdAt)
            .where { GroupMembers.groupId eq groupId }
            .orderBy(Users.id.asc())
            .limit(limit, offset)
            .mapNotNull { row ->
                val id = row[Users.id] ?: return@mapNotNull null
                DbUser(
                    id = id,
                    name = row[Users.name]!!,
                    email = row[Users.email]!!,
                    password = row[Users.password]!!,
                    createdAt = row[Users.createdAt]
                )
            }
    }

    /** Convenience to fetch a group and its members together. */
    fun getByIdWithMembers(id: Long): Pair<DbGroup, List<DbUser>>? {
        val grp = getById(id) ?: return null
        val members = getMembers(id, limit = Int.MAX_VALUE, offset = 0)
        return grp to members
    }

    // endregion
}
