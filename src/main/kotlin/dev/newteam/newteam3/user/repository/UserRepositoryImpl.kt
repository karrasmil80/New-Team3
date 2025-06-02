package dev.newteam.newteam3.user.repository

import dev.newteam.newteam3.user.dao.UserDao
import dev.newteam.newteam3.user.mapper.toModel
import dev.newteam.newteam3.user.model.User

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override fun findAll(): List<User> {
        return userDao.findAll().map { it.toModel() }
    }

    override fun findById(id: Int): User? {
        return userDao.findById(id)!!.toModel()
    }

    override fun save(user: User): User {
        val existingUser = user.id.let { userDao.findById(it) }
        return if (existingUser == null) {
            val savedEntity = userDao.save(user.id, user.nombre, user.password)
            User(
                id = savedEntity.id,
                nombre = savedEntity.nombre,
                password = savedEntity.password
            )
        } else {
            user
        }
    }


    override fun deleteById(id: Int): Int {
        return userDao.deleteById(id)
    }

    override fun deleteAll(): Int {
        return userDao.deleteAll()
    }

    override fun saveAll(users: List<User>): List<User> {
        return users.map { save(it) }
    }
}
