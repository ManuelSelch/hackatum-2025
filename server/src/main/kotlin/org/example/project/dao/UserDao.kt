package org.example.project.dao

import org.example.project.database.UsersTable
import org.example.project.domain.entities.UserEntity
import org.example.project.domain.models.User
import org.jetbrains.exposed.sql.transactions.transaction

class UserDao() {
    fun create(newUser: User): User {
        return transaction {
            UserEntity.new {
                this.name = newUser.name
                this.email = newUser.email
                this.password = newUser.password
            }.toModel()
        }
    }

    fun getById(id: Long): User? = transaction {
        UserEntity.findById(id)?.toModel()
    }

    fun getByEmail(email: String): User? = transaction {
        UserEntity.find { UsersTable.email eq email }
            .limit(1)
            .firstOrNull()
            ?.toModel()
    }

    fun update(user: User): User? = transaction {
        UserEntity.findById(user.id)?.apply {
            this.name = user.name
            this.email = user.email
        }?.toModel()
    }

    fun deleteById(id: Long): Boolean = transaction {
        val entity = UserEntity.findById(id)
        if (entity != null) {
            entity.delete()
            true
        } else {
            false
        }
    }
}