package validator

import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Persona
import dev.newteam.newteam3.plantilla.validator.EntrenadorValidator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDate

class EntrenadorValidatorTest {
    private val validator = EntrenadorValidator()

    @Test
    @DisplayName("Test de Entrenador Válido.")
    fun validateEntrenadorValido() {
        val entrenador = Entrenador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Entrenador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            equipo = Persona.Equipos.NEW_TEAM,
            especializacion = Entrenador.Especializacion.PRINCIPAL
        )

        val result = validator.validate(entrenador)

        assertTrue(result.isOk, "El validador debe devolver Ok")
        assertEquals(entrenador, result.value, "El validador debe devolver el mismo entrenador.")
    }


    @Test
    @DisplayName("Test de Entrenador sin Nombre.")
    fun validateEntrenadorNombreVacio() {
        val entrenador = Entrenador (
            id = 1,
            nombre = "",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Entrenador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            equipo = Persona.Equipos.NEW_TEAM,
            especializacion = Entrenador.Especializacion.PRINCIPAL
        )

        val result = validator.validate(entrenador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El nombre no puede estar en blanco.", result.error.message)
    }

    @Test
    @DisplayName("Test de Entrenador con Nombre inválido.")
    fun validateEntrenadorNombreInvalido() {
        val entrenador = Entrenador (
            id = 1,
            nombre = "C",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Entrenador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            equipo = Persona.Equipos.NEW_TEAM,
            especializacion = Entrenador.Especializacion.PRINCIPAL
        )

        val result = validator.validate(entrenador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El nombre debe tener entre 2 y 27 caracteres.", result.error.message)
    }


    @Test
    @DisplayName("Test de Entrenador sin Apellido.")
    fun validateEntrenadorApellidoVacio() {
        val entrenador = Entrenador (
            id = 1,
            nombre = "Carlos",
            apellido = "",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Entrenador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            equipo = Persona.Equipos.NEW_TEAM,
            especializacion = Entrenador.Especializacion.PRINCIPAL
        )

        val result = validator.validate(entrenador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El apellido no puede estar en blanco.", result.error.message)
    }

    @Test
    @DisplayName("Test de Entrenador con Apellido inválido.")
    fun validateEntrenadorApellidoInvalido() {
        val entrenador = Entrenador (
            id = 1,
            nombre = "Carlos",
            apellido = "A",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Entrenador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            equipo = Persona.Equipos.NEW_TEAM,
            especializacion = Entrenador.Especializacion.PRINCIPAL
        )

        val result = validator.validate(entrenador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El apellido debe tener entre 2 y 40 caracteres.", result.error.message)
    }


    @Test
    @DisplayName("Test de Entrenador recién nacido.")
    fun validateEntrenadorRecienNacido() {
        val entrenador = Entrenador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.now(),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Entrenador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            equipo = Persona.Equipos.NEW_TEAM,
            especializacion = Entrenador.Especializacion.PRINCIPAL
        )

        val result = validator.validate(entrenador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. La fecha de nacimiento no puede ser hoy.", result.error.message)
    }

    @Test
    @DisplayName("Test de Entrenador que aún no ha nacido.")
    fun validateEntrenadorFuturo() {
        val entrenador = Entrenador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2026, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Entrenador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            equipo = Persona.Equipos.NEW_TEAM,
            especializacion = Entrenador.Especializacion.PRINCIPAL
        )

        val result = validator.validate(entrenador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. La fecha de nacimiento no puede ser en el futuro.", result.error.message)
    }



    @Test
    @DisplayName("Test de Entrenador que aún no se ha incorporado")
    fun validateEntrenadorIncorporacionFutura() {
        val entrenador = Entrenador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2027, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Entrenador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            equipo = Persona.Equipos.NEW_TEAM,
            especializacion = Entrenador.Especializacion.PRINCIPAL
        )

        val result = validator.validate(entrenador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. La fecha de incorporación no puede ser en el futuro.", result.error.message)
    }


    @Test
    @DisplayName("Test de Entrenador con Salario Negativo.")
    fun validateEntrenadorSalarioNegativo() {
        val entrenador = Entrenador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = -1000.0,
            pais = "España",
            rol = "Entrenador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            equipo = Persona.Equipos.NEW_TEAM,
            especializacion = Entrenador.Especializacion.PRINCIPAL
        )

        val result = validator.validate(entrenador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El salario debe ser mayor que 0.", result.error.message)
    }

    @Test
    @DisplayName("Test de Entrenador con Salario Bajo.")
    fun validateEntrenadorSalarioBajo() {
        val entrenador = Entrenador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 40.0,
            pais = "España",
            rol = "Entrenador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            equipo = Persona.Equipos.NEW_TEAM,
            especializacion = Entrenador.Especializacion.PRINCIPAL
        )

        val result = validator.validate(entrenador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El salario mínimo es de 1000€ 🤑", result.error.message)
    }

    @Test
    @DisplayName("Test de Entrenador sin País.")
    fun validateEntrenadorPaisVacio() {
        val entrenador = Entrenador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "",
            rol = "Entrenador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            equipo = Persona.Equipos.NEW_TEAM,
            especializacion = Entrenador.Especializacion.PRINCIPAL
        )

        val result = validator.validate(entrenador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El país no puede estar en blanco.", result.error.message)
    }

    @Test
    @DisplayName("Test de Entrenador con País corto.")
    fun validateEntrenadorPaisCorto() {
        val entrenador = Entrenador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "E",
            rol = "Entrenador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            equipo = Persona.Equipos.NEW_TEAM,
            especializacion = Entrenador.Especializacion.PRINCIPAL
        )

        val result = validator.validate(entrenador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El país debe tener entre 4 y 47 caracteres.", result.error.message)
    }


    /*@Test
    @DisplayName("Test de Entrenador sin Rol.")
    fun validateEntrenadorRolVacio() {
        val entrenador = Entrenador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            equipo = Persona.Equipos.NEW_TEAM,
            especializacion = Entrenador.Especializacion.PRINCIPAL
        )

        val result = validator.validate(entrenador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El rol no puede estar en blanco.", result.error.message)
    }*/
}