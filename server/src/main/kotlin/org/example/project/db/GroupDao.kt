package org.example.project.db
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.and

/**
 * DAO for groups and group membership using Exposed entities.
 * Uses a junction table (group_members) to relate users to groups.
 */
class GroupDao(private val dbManager: DatabaseManager) {

    private val database get() = dbManager.getDatabase()

    // region Groups CRUD

    /** Create a new group. Returns the created Group with generated id. */
    fun create(name: String): Group {
        return transaction(database) {
            GroupEntity.new {
                this.name = name
                this.createdAt = System.currentTimeMillis().toString()
            }.toModel()
        }
    }

    /** Retrieve a group by id, or null if not found. Includes members automatically. */
    fun getById(id: Long): Group? = transaction(database) {
        GroupEntity.findById(id)?.toModel()
    }

    /** List groups with pagination. */
    fun list(limit: Int = 100, offset: Long = 0): List<Group> = transaction(database) {
        GroupEntity.all()
            .orderBy(GroupsTable.id to SortOrder.ASC)
            .limit(limit, offset)
            .map { it.toModel() }
    }

    /** Delete a group by id. Returns true if a row was deleted. */
    fun deleteById(id: Long): Boolean = transaction(database) {
        val entity = GroupEntity.findById(id)
        if (entity != null) {
            entity.delete()
            true
        } else {
            false
        }
    }

    /** Count total groups. */
    fun count(): Long = transaction(database) {
        GroupEntity.count()
    }

    // endregion

    // region Membership management

    /**
     * Add a user to a group. Returns true if inserted, false if it already existed (idempotent).
     * GroupMember is internal and not exposed.
     */
    fun addUser(groupId: Long, userId: Long): Boolean = transaction(database) {
        val group = GroupEntity.findById(groupId) ?: return@transaction false
        val user = UserEntity.findById(userId) ?: return@transaction false

        // Check if user already exists in group
        if (group.members.any { it.id.value == userId }) {
            return@transaction false // Already a member
        }

        // Add user to group
        group.members = SizedCollection(group.members + user)
        return@transaction true
    }


    /** Remove a user from a group. Returns true if a row was deleted. */
    fun removeUser(groupId: Long, userId: Long): Boolean = transaction(database) {
        val group = GroupEntity.findById(groupId) ?: return@transaction false
        val user = UserEntity.findById(userId) ?: return@transaction false

        // Check if user is a member of the group
        if (!group.members.any { it.id.value == userId }) {
            return@transaction false // Not a member
        }

        // Remove user from group
        group.members = SizedCollection(group.members - user)
        return@transaction true
    }
}
