package dev.newteam.newteam3.convocatoria.validator

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import dev.newteam.newteam3.convocatoria.error.ConvocatoriaError

import dev.newteam.newteam3.convocatoria.models.Convocatoria
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

/**
 * En esta clase se almacenan los condicionales que validaran la entrada de datos de la convocatoria.
 */

private val logger = logging()
class ConvocatoriaValidator: Validator<Convocatoria, ConvocatoriaError> {
    override fun validate(t: Convocatoria): Result<Convocatoria, ConvocatoriaError> {
        logger.debug { "🔵 Validando la convocatoria..." }


        if (t.id <= 0) {
            return Err(ConvocatoriaError.ConvocatoriaValidatorError("El ID de la convocatoria debe ser mayor a 0."))
        }


        if (t.jornada.isAfter(LocalDateTime.now())) {
            return Err(ConvocatoriaError.ConvocatoriaValidatorError("La fecha de la jornada no puede estar en el futuro."))
        }


        if (t.descripcion.isBlank()) {
            return Err(ConvocatoriaError.ConvocatoriaValidatorError("La descripción no puede estar vacía."))
        }

        if (t.descripcion.length < 10 || t.descripcion.length > 300) {
            return Err(ConvocatoriaError.ConvocatoriaValidatorError("La descripción debe tener entre 10 y 300 caracteres."))
        }


        if (t.jugadoresConvocados.isEmpty()) {
            return Err(ConvocatoriaError.ConvocatoriaValidatorError("Debe haber al menos un jugador convocado."))
        }

        /*
        if (t.onceTitular.isEmpty()) {
            return Err(ConvocatoriaError.ConvocatoriaValidatorError("Debe haber al menos un jugador en el once titular."))
        }

        if (t.onceTitular.size > 11) {
            return Err(ConvocatoriaError.ConvocatoriaValidatorError("El once titular no puede tener más de 11 jugadores."))
        }
         */
        return Ok(t)
    }
}

