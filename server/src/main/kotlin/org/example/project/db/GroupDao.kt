package org.example.project.db
import io.ktor.http.HttpStatusCode
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

    fun addExpense(groupID: Long, amount: Double, payerID: Long, borrowers: List<Long>, description: String): Result<Expense> = transaction(database) {
        runCatching {
            val group = GroupEntity.findById(groupID) ?: throw IllegalArgumentException("GroupID is invalid. No matching Group found!")
            val payer = UserEntity.findById(payerID) ?: throw IllegalArgumentException("PayerID is invalid. No matching Payer found!")
            val borrowerEntities = UserEntity.find { UsersTable.id inList borrowers }.toList()

            if (borrowerEntities.size != borrowers.size) {
                throw IllegalArgumentException("One or more borrower IDs are invalid")
            }

            if (!borrowerEntities.contains(payer)) {
                throw IllegalArgumentException("Payer must be one of the borrowers")
            }

            val expense = ExpenseEntity.new {
                this.groupId = group.id
                this.amount = amount.toBigDecimal()
                this.payer = payer
                this.description = description
            }

            expense.toModel()
        }
    }
}
