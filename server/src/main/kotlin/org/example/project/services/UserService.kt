package org.example.project.services

import org.example.project.dao.UserDao
import org.example.project.domain.entities.UserEntity

class UserService(userDao: UserDao) {
    val dao = userDao

    fun update(userDTO: models.UserDTO) {
        val oldUser = UserEntity.findById(userDTO.id)
        oldUser?.apply {
            this.name = userDTO.name
            this.email = userDTO.email
        }?.toModel()
    }
}