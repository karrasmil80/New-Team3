package validator

import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona
import dev.newteam.newteam3.plantilla.validator.JugadorValidator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import java.time.LocalDate


class JugadorValidatorTest {
    private val validator = JugadorValidator()

    @Test
    @DisplayName("Test de Jugador Válido.")
    fun validateJugadorValido() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isOk, "El validador debe devolver Ok")
        assertEquals(jugador, result.value, "El validador debe devolver el mismo jugador.")
    }


    @Test
    @DisplayName("Test de Jugador sin Nombre.")
    fun validateJugadorNombreVacio() {
        val jugador = Jugador (
            id = 1,
            nombre = "",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El nombre no puede estar en blanco", result.error.message)
    }

    @Test
    @DisplayName("Test de Jugador con Nombre inválido.")
    fun validateJugadorNombreInvalido() {
        val jugador = Jugador (
            id = 1,
            nombre = "C",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El nombre debe tener entre 2 y 27 caracteres", result.error.message)
    }


    @Test
    @DisplayName("Test de Jugador sin Apellido.")
    fun validateJugadorApellidoVacio() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El apellido no puede estar en blanco", result.error.message)
    }

    @Test
    @DisplayName("Test de Jugador con Apellido inválido.")
    fun validateJugadorApellidoInvalido() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "AlcarazAlcarazAlcarazAlcarazAlcarazAlcarazAlcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El apellido debe tener entre 2 y 40 caracteres", result.error.message)
    }


    @Test
    @DisplayName("Test de Jugador recién nacido.")
    fun validateJugadorRecienNacido() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.now(),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. La fecha de nacimiento no puede ser hoy", result.error.message)
    }

    @Test
    @DisplayName("Test de Jugador que aún no ha nacido.")
    fun validateJugadorFuturo() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2026, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. La fecha de nacimiento no puede ser en el futuro", result.error.message)
    }



    @Test
    @DisplayName("Test de Jugador que aún no se ha incorporado ")
    fun validateJugadorIncorporacionFutura() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2026, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. La fecha de incorporación no puede ser en el futuro", result.error.message)
    }


    @Test
    @DisplayName("Test de Jugador con Salario Negativo.")
    fun validateJugadorSalarioNegativo() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = -1.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El salario debe ser mayor que 0", result.error.message)
    }

    @Test
    @DisplayName("Test de Jugador con Salario Bajo.")
    fun validateJugadorSalarioBajo() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 999.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El salario mínimo es de 1000€ 🤑.", result.error.message)
    }

    @Test
    @DisplayName("Test de Jugador sin País.")
    fun validateJugadorPaisVacio() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El país no puede estar en blanco", result.error.message)
    }

    @Test
    @DisplayName("Test de Jugador con País corto.")
    fun validateJugadorPaisCorto() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "Esp",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El país debe tener entre 4 y 47 caracteres.", result.error.message)
    }


    /*@Test
    @DisplayName("Test de Jugador sin Rol.")
    fun validateJugadorRolVacio() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El rol no puede estar en blanco.", result.error.message)
    }*/

    @Test
    @DisplayName("Test de Jugador con Dorsal negativo.")
    fun validateJugadorDorsalNegativo() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = -7,
            altura = 1.83,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El dorsal debe ser superior a 0.", result.error.message)
    }

    @Test
    @DisplayName("Test de Jugador demasiado bajo.")
    fun validateJugadorBajito() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.25,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. La altura mínima es de 1.30m.", result.error.message)
    }

    @Test
    @DisplayName("Test de Jugador demasiado alto.")
    fun validateJugadorGigante() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 3.10,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. No se admiten jugadores tan altos.", result.error.message)
    }

    @Test
    @DisplayName("Test de Jugador flaco.")
    fun validateJugadorFlaco() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 33.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El peso mínimo es de 40kg.", result.error.message)
    }

    @Test
    @DisplayName("Test de Jugador rellenito.")
    fun validateJugadorRellenito() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 174.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El peso máximo es de 150kg.", result.error.message)
    }

    @Test
    @DisplayName("Test de Jugador con Goles negativos.")
    fun validateJugadorGolesNgeativos() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 74.0,
            goles = -1,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. No puede tener goles negativos.", result.error.message)
    }

    @Test
    @DisplayName("Test de Jugador con Media de Goles negativa.")
    fun validateJugadorMediaGolesNegativa() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = -1.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. No puede tener una media goles negativos.", result.error.message)
    }

    @Test
    @DisplayName("Test de Jugador con Partidos Jugados negativos.")
    fun validateJugadorPartidosJugadosNegativos() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 74.0,
            goles = 0,
            partidosJugados = -1,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = 0
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. No puede haber jugado partidos negativos.", result.error.message)
    }

    @Test
    @DisplayName("Test de Jugador con Minutos Jugados negativos.")
    fun validateJugadorMinutosJugadosNegativos() {
        val jugador = Jugador (
            id = 1,
            nombre = "Carlos",
            apellido = "Alcaraz",
            fechaNacimiento = LocalDate.of(2003, 5, 5),
            fechaIncorporacion = LocalDate.of(2025, 5, 20),
            salario = 4000.0,
            pais = "España",
            rol = "Jugador",
            imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.83,
            peso = 74.0,
            goles = 0,
            partidosJugados = 0,
            equipo = Persona.Equipos.MUPPET,
            mediaGoles = 0.0,
            minutosJugados = -1
        )

        val result = validator.validate(jugador)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. No puede haber jugado minutos negativos.", result.error.message)
    }
}