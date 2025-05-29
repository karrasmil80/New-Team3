package dev.newteam.newteam3.user.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.newteam.newteam3.plantilla.error.PersonaError
import dev.newteam.newteam3.plantilla.validator.Validator
import dev.newteam.newteam3.user.model.User
import org.lighthousegames.logging.logging

/**
 * Aquí se almacena el método que validará la entrada de datos del Usuario.
 */
class UserValidator: Validator<User, PersonaError> {
    private val logger = logging()

    /**
     * Método que valida la entrada de datos del Usuario.
     */
    override fun validate(t: User): Result<User, PersonaError> {
        logger.debug { "🔵 Validando la entrada de datos del Usuario..." }

        if (t.id <= 0) {
            return Err(PersonaError.PersonaValidatorError("ID negativo/nulo."))
        }

        if (t.nombre != "admin"){
            return Err(PersonaError.PersonaValidatorError("Nombre incorrecto."))
        }

        if (t.password != "contra123") {
            return Err(PersonaError.PersonaValidatorError("Contraseña incorrecta."))
        }


        return Ok(t)
    }
}