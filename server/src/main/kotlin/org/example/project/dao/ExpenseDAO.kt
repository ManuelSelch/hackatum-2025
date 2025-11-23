package org.example.project.dao

import org.example.project.database.GroupsTable
import org.example.project.domain.entities.ExpenseEntity
import org.example.project.domain.entities.UserEntity
import org.example.project.domain.models.Expense
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.transactions.transaction

class ExpenseDAO {
    fun create(groupID: Long, payer: UserEntity, amount: Double, description: String, borrowers: List<UserEntity>): Expense = transaction {
        ExpenseEntity.new{
            this.groupID = EntityID(groupID, GroupsTable)
            this.payer = payer
            this.amount = amount.toBigDecimal()
            this.description = description
            this.borrowers = SizedCollection(borrowers)
        }.toModel()
    }
}