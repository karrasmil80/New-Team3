package dev.newteam.newteam3.convocatoria.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.newteam.newteam3.convocatoria.error.ConvocatoriaError
import dev.newteam.newteam3.convocatoria.models.Convocatoria
import org.lighthousegames.logging.logging
import java.time.LocalDate

private val logger = logging()
class ConvocatoriaValidator : Validator<Convocatoria, ConvocatoriaError> {
    override fun validate(t: Convocatoria): Result<Convocatoria, ConvocatoriaError> {

        if (t.id < 0) {
            return Err(ConvocatoriaError.ConvocatoriaValidatorError("El id de la convocatoria no puede ser negativo"))
        }

        if (t.jornada.isBefore(LocalDate.of(1970, 1, 1))) {
            return Err(ConvocatoriaError.ConvocatoriaValidatorError("La jornada no puede ser anterior al año 2000"))
        }

        if (t.jornada.isAfter(LocalDate.now().plusYears(1))) {
            return Err(ConvocatoriaError.ConvocatoriaValidatorError("La jornada no puede ser en el futuro"))
        }

        if (t.personalList.isEmpty()) {
            return Err(ConvocatoriaError.ConvocatoriaValidatorError("La lista de convocados no puede estar vacía"))
        }

        logger.debug { "Convocatoria validada correctamente" }
        return Ok(t)
    }

}