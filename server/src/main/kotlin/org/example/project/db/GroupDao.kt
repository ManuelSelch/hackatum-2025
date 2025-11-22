package org.example.project.db
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * DAO for groups and group membership using Exposed entities.
 * Uses a junction table (group_members) to relate users to groups.
 */
class GroupDao(private val dbManager: DatabaseManager) {

    private val database get() = dbManager.getDatabase()

    /** Create a new group. Returns the created Group with generated id. */
    fun create(name: String, creatorID: Long): Group {
        return transaction(database) {
            GroupEntity.new {
                this.name = name
                this.members = SizedCollection(UserEntity[creatorID])
                this.createdAt = System.currentTimeMillis().toString()
            }.toModel()
        }
    }

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

    fun listUserGroups(userID: Long): List<Group> = transaction(database) {
        val user = UserEntity[userID]
        return@transaction user.groups.map { it.toModel() }
    }

}
