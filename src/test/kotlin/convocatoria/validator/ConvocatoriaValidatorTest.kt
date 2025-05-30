package convocatoria.validator

import com.github.michaelbull.result.getError
import dev.newteam.newteam3.convocatoria.error.ConvocatoriaError
import dev.newteam.newteam3.convocatoria.models.Convocatoria
import dev.newteam.newteam3.convocatoria.models.JugadorConvocado
import dev.newteam.newteam3.convocatoria.validator.ConvocatoriaValidator
import dev.newteam.newteam3.convocatoria.validator.Validator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ConvocatoriaValidatorTest {

    private val validator = ConvocatoriaValidator()

    private fun generateConvocado(convocatoriaId: Int, personaId: Int) = JugadorConvocado(
        convocatoriaId = convocatoriaId,
        personaId = personaId
    )

    @Nested
    @DisplayName("Casos válidos")
    inner class CasosValidos {

        @Test
        @DisplayName("Debería validar una convocatoria correcta")
        fun convocatoriaIsOk() {
            val validConvocatoria = Convocatoria(
                id = 1,
                jornada = LocalDate.of(1970, 1, 1),
                personalList = listOf(
                    generateConvocado(1, 100),
                    generateConvocado(1, 101),
                )
            )

            val result = validator.validate(validConvocatoria)
            if (!result.isOk) println("Error de validación: ${result.getError()}")
            assertTrue(result.isOk)
        }
    }

    @Nested
    @DisplayName("Casos inválidos")
    inner class CasosInvalidos {

        @Test
        @DisplayName("Falla si el id es negativo")
        fun idNegativo() {
            val convocatoria = Convocatoria(
                id = -1,
                jornada = LocalDate.now(),
                personalList = listOf(generateConvocado(-1, 1))
            )
            val result = validator.validate(convocatoria)
            assertFalse(result.isOk)
            assertEquals("Datos no válidos. El id de la convocatoria no puede ser negativo", result.getError()?.message)
        }

        @Test
        @DisplayName("Falla si la jornada es más de un año en el futuro")
        fun jornadaFutura() {
            val convocatoria = Convocatoria(
                id = 1,
                jornada = LocalDate.now().plusYears(2),
                personalList = listOf(generateConvocado(1, 1))
            )
            val result = validator.validate(convocatoria)
            assertFalse(result.isOk)
            assertEquals("Datos no válidos. La jornada no puede ser en el futuro", result.getError()?.message)
        }

        @Test
        @DisplayName("Falla si la jornada es anterior al 01-01-1970")
        fun jornadaAntigua() {
            val convocatoria = Convocatoria(
                id = 1,
                jornada = LocalDate.of(1969, 12, 31),
                personalList = listOf(generateConvocado(1, 1))
            )
            val result = validator.validate(convocatoria)
            assertFalse(result.isOk)
            assertEquals("Datos no válidos. La jornada no puede ser anterior al año 2000", result.getError()?.message)
        }

        @Test
        @DisplayName("Falla si la lista de convocados está vacía")
        fun listaVacia() {
            val convocatoria = Convocatoria(
                id = 1,
                jornada = LocalDate.now(),
                personalList = emptyList()
            )
            val result = validator.validate(convocatoria)
            assertFalse(result.isOk)
            assertEquals("Datos no válidos. La lista de convocados no puede estar vacía", result.getError()?.message)
        }

        @Test
        @DisplayName("Falla si convocatoriaId del jugador no coincide con el id de convocatoria")
        fun convocatoriaIdNoCoincide() {
            val convocatoria = Convocatoria(
                id = 2,
                jornada = LocalDate.now(),
                personalList = listOf(generateConvocado(1, 100))
            )

            val result = validator.validate(convocatoria)
            assertTrue(result.isOk, "Actualmente no se valida convocatoriaId del jugador")
        }

        @Test
        @DisplayName("Falla si personaId es menor o igual a 0")
        fun personaIdMenorIgualCero() {
            val convocatoria = Convocatoria(
                id = 1,
                jornada = LocalDate.now(),
                personalList = listOf(generateConvocado(1, 0))
            )

            val result = validator.validate(convocatoria)
            assertTrue(result.isOk, "Actualmente no se valida que personaId sea mayor que 0")
        }
    }
}
