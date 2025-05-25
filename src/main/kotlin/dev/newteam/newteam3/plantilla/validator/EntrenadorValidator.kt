package dev.newteam.newteam3.plantilla.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

import dev.newteam.newteam3.plantilla.error.PersonaError
import dev.newteam.newteam3.plantilla.models.Entrenador

import org.lighthousegames.logging.logging
import java.time.LocalDate

/**
 * En esta clase se almacena la función validate que
 * se aplicará a la entrada de datos de un Entrenador.
 */
class EntrenadorValidator: Validator<Entrenador, PersonaError> {
    private val logger = logging()

    /**
     * Función que se encargará de almacenar todos los condicionales
     * que validarán al Jugador.
     */
    override fun validate(t: Entrenador): Result<Entrenador, PersonaError> {
        logger.debug { "Validando la entrada de datos del Entrenador..." }

        if (t.nombre.isBlank()) {
            return Err(PersonaError.PersonaValidatorError("El nombre no puede estar en blanco."))
        }
        if (t.nombre.length < 2 || t.nombre.length > 27) {
            return Err(PersonaError.PersonaValidatorError("El nombre debe tener entre 2 y 27 caracteres."))
        }

        if (t.apellido.isBlank()) {
            return Err(PersonaError.PersonaValidatorError("El apellido no puede estar en blanco."))
        }
        if (t.apellido.length < 2 || t.apellido.length > 40) {
            return Err(PersonaError.PersonaValidatorError("El apellido debe tener entre 2 y 40 caracteres."))
        }

        if (t.fechaNacimiento == LocalDate.now()) {
            return Err(PersonaError.PersonaValidatorError("La fecha de nacimiento no puede ser hoy."))
        }
        if (t.fechaNacimiento.isAfter(LocalDate.now())) {
            return Err(PersonaError.PersonaValidatorError("La fecha de nacimiento no puede ser en el futuro."))
        }

        if (t.fechaIncorporacion.isAfter(LocalDate.now())) {
            return Err(PersonaError.PersonaValidatorError("La fecha de incorporación no puede ser en el futuro."))
        }

        if (t.salario <= 0.0) {
            return Err(PersonaError.PersonaValidatorError("El salario debe ser mayor que 0."))
        }
        if (t.salario < 1000.0) {
            return Err(PersonaError.PersonaValidatorError("El salario mínimo es de 1000€ 🤑"))
        }

        if (t.pais.isBlank()) {
            return Err(PersonaError.PersonaValidatorError("El país no puede estar en blanco."))
        }
        if (t.pais.length < 4 || t.pais.length > 47) {
            return Err(PersonaError.PersonaValidatorError("El país debe tener entre 4 y 47 caracteres."))
        }

        if (t.rol.isBlank()) {
            return Err(PersonaError.PersonaValidatorError("El rol no puede estar en blanco."))
        }

        return Ok(t)
    }
}