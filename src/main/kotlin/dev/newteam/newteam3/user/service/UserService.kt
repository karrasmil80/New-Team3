package dev.newteam.newteam3.user.service

import com.github.michaelbull.result.Result
import dev.newteam.newteam3.plantilla.error.PersonaError
import dev.newteam.newteam3.plantilla.models.Persona
import dev.newteam.newteam3.user.error.UserError
import dev.newteam.newteam3.user.model.User

interface UserService {
    fun findAll(): Result<List<User>, UserError>
    fun findById(id: Int): Result<User?, UserError>
    fun save(user: User): Result<User, UserError>
    fun deleteById(id: Int): Result<Int, UserError>
    fun deleteAll(): Result<Int, UserError>
    fun saveAll(user: List<User>): Result<List<User>, UserError>
}