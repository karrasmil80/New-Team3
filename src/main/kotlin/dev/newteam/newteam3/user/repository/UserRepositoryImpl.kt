package dev.newteam.newteam3.user.repository

import dev.newteam.newteam3.user.dao.UserDao
import dev.newteam.newteam3.user.model.User

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {
    override fun findAll(): List<User> {
        TODO("SS")
    }

    override fun findById(id: Int): User? {
        TODO("Not yet implemented")
    }

    override fun save(user: User): User {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int): Int {
        TODO("Not yet implemented")
    }

    override fun deleteAll(): Int {
        TODO("Not yet implemented")
    }

    override fun saveAll(user: List<User>): List<User> {
        TODO("Not yet implemented")
    }
}