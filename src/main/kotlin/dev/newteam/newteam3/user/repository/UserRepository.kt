package dev.newteam.newteam3.user.repository

import dev.newteam.newteam3.user.model.User

interface UserRepository {
    fun findAll(): List<User>
    fun findById(id: Int): User?
    fun save(user: User): User
    fun deleteById(id: Int) : Int
    fun deleteAll() : Int
    fun saveAll(user : List<User>): List<User>
}