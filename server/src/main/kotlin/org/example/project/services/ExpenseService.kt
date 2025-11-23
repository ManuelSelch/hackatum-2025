package org.example.project.services

import org.example.project.dao.ExpenseDAO
import org.example.project.dao.GroupDao
import org.example.project.domain.entities.UserEntity
import org.example.project.domain.models.Expense

class ExpenseService(expenseDao: ExpenseDAO, groupDAO: GroupDao) {
    val dao = expenseDao
    val groupDao = groupDAO

    fun createExpense(groupID: Long, payerID: Long, amount: Double, description: String, borrowerID: List<Long>): Result<Expense> = runCatching {
        // Check if payer exists
        val payer = UserEntity.findById(payerID) ?: throw IllegalArgumentException("UserID is invalid. No matching User found!")

        // Check if all borrower exist
        if (!borrowerID.all { UserEntity.findById(it) != null }) throw IllegalArgumentException("Invalid borrowers")

        // Check if payer & borrower in group
        val group = groupDao.getGroupByID(groupID) ?: throw IllegalArgumentException("GroupID is invalid. No matching Group found!")
        val memberIDs = group.members.map { it.id }
        if((borrowerID + payerID).any { it !in memberIDs}) throw IllegalArgumentException("A User is not a member of this group")

        // Check if payer in borrower
        if (payerID !in borrowerID) throw IllegalArgumentException("Payer must be one of the borrowers")

        val b = borrowerID.map { UserEntity[it] }

        dao.create(group.id, payer, amount, description, b)
    }
}