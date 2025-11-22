package org.example.project.dao

import org.example.project.database.Expense
import org.example.project.database.UsersTable
import org.example.project.domain.entities.ExpenseEntity
import org.example.project.domain.entities.GroupEntity
import org.example.project.domain.entities.UserEntity
import org.example.project.domain.models.Group
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.transactions.transaction

class GroupDao() {

    fun create(name: String, creatorID: Long): Group {
        return transaction {
            GroupEntity.new {
                this.name = name
                this.members = SizedCollection(UserEntity[creatorID])
            }.toModel()
        }
    }

    fun addUser(groupID: Long, userID: Long): Result<Group> = transaction {
        runCatching {
            val group = GroupEntity.findById(groupID) ?: throw IllegalArgumentException("GroupID is invalid. No matching Group found!")
            val user = UserEntity.findById(userID) ?: throw IllegalArgumentException("UserID is invalid. No matching User found!")

            // Check if user already exists in group
            if (group.members.any { it.id.value == userID }) throw IllegalArgumentException("User is already a member of this group")

            // Add user to group
            group.members = SizedCollection(group.members + user)
            return@transaction Result.success(group.toModel())
        }
    }

    fun removeUser(groupID: Long, userID: Long): Result<Group> = transaction {
        runCatching {
            val group = GroupEntity.findById(groupID) ?: throw IllegalArgumentException("GroupID is invalid. No matching Group found!")
            val user = UserEntity.findById(userID) ?: throw IllegalArgumentException("UserID is invalid. No matching User found!")

            // Check if user in group
            if (group.members.all { it.id.value != userID }) throw IllegalArgumentException("User is not a member of this group")

            group.members = SizedCollection(group.members - user)
            return@transaction Result.success(group.toModel())
        }
    }

    fun getGroupsByUserID(userID: Long): List<Group> = transaction {
        val user = UserEntity[userID]
        return@transaction user.groups.map { it.toModel() }
    }

    fun getGroupByID(groupID: Long): Group? = transaction {
        GroupEntity.findById(groupID)?.toModel()
    }

    fun addExpense(groupID: Long, amount: Double, payerID: Long, borrowers: List<Long>, description: String): Result<Expense> = transaction {
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
