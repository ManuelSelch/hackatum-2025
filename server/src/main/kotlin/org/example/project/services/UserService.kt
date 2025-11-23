package org.example.project.services

import org.example.project.dao.UserDao
import org.example.project.domain.models.User

class UserService(userDao: UserDao) {
    val dao = userDao
    
    fun update(userDTO: models.UserDTO): User? {
        return dao.update(User(
            id = userDTO.id,
            name = userDTO.name,
            email = userDTO.email,
            password = ""
        ))
    }
}