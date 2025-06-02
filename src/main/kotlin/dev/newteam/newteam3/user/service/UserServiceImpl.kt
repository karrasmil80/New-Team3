package dev.newteam.newteam3.user.service

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Err
import dev.newteam.newteam3.user.error.UserError
import dev.newteam.newteam3.user.model.User
import dev.newteam.newteam3.user.repository.UserRepository
import org.lighthousegames.logging.logging

class UserServiceImpl(
    private val repository: UserRepository
) : UserService {

    private val logger = logging()

    override fun findAll(): Result<List<User>, UserError> {
        logger.debug { "Buscando todos los usuarios" }
        return Ok(repository.findAll())
    }

    override fun findById(id: Int): Result<User?, UserError> {
        logger.debug { "Buscando usuario con id = $id" }
        val user = repository.findById(id)
        return if (user != null) {
            Ok(user)
        } else {
            Err(UserError.UserIdNotFound(id))
        }
    }

    override fun save(user: User): Result<User, UserError> {
        logger.debug { "Guardando usuario con id = ${user.id}" }
        return try {
            val saved = repository.save(user)
            Ok(saved)
        } catch (e: Exception) {
            Err(UserError.UserServiceException("No se ha podido guardar el usuario"))
        }
    }

    override fun deleteById(id: Int): Result<Int, UserError> {
        logger.debug { "Eliminando usuario con id = $id" }
        return try {
            val deleted = repository.deleteById(id)
            Ok(deleted)
        } catch (e: Exception) {
            Err(UserError.UserServiceException("No se ha podido borrar por id al usuario"))
        }
    }

    override fun deleteAll(): Result<Int, UserError> {
        logger.debug { "Eliminando todos los usuarios" }
        return try {
            val deleted = repository.deleteAll()
            Ok(deleted)
        } catch (e: Exception) {
            Err(UserError.UserServiceException("No se pudieron eliminar todos los usuarios"))
        }
    }

    override fun saveAll(users: List<User>): Result<List<User>, UserError> {
        logger.debug { "Guardando ${users.size} usuarios" }
        return try {
            val saved = repository.saveAll(users)
            Ok(saved)
        } catch (e: Exception) {
            Err(UserError.UserServiceException("Error al guardar todos los usuarios"))
        }
    }
}
