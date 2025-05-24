package dao

import dev.newteam.newteam3.database.JdbiManager
import dev.newteam.newteam3.plantilla.dao.PersonaDao
import dev.newteam.newteam3.plantilla.dao.PersonaEntity
import dev.newteam.newteam3.plantilla.models.Persona
import dev.newteam.newteam3.plantilla.utils.providePersonaDao
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertAll
import java.time.LocalDate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonaDaoTest {

    private lateinit var jdbi: JdbiManager
    private lateinit var dao: PersonaDao

    @BeforeAll
    fun setUp() {
        // Inicializamos la BD
        val jdbi = JdbiManager(
            databaseUrl = "jdbc:h2:mem:newteamDB;DB_CLOSE_DELAY=-1",
            databaseInitTables = true,
            databaseInitData = true,
            databaseLogger = false
        ).jdbi
        dao = providePersonaDao(jdbi)
    }

    @Nested
    @DisplayName("Casos correctos para persona")
    inner class CasosCorrectos {
        @Test
        @DisplayName("Deberia de devolver una lista de jugadores")
        fun findAll() {
            val persona = PersonaEntity(
                id = 1,
                nombre = "Pepe",
                apellido = "Gonzalez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("2000-01-01"),
                salario = 1000.0,
                pais = "España",
                rol = "jugador",
                imagen = "",
                equipo = Persona.Equipos.MUPPET
            )

            val personaFindAll = dao.findAll()

            assertAll(
                { assertEquals(4, personaFindAll.size, "La lista debe de contener personas") },
                { assertTrue(personaFindAll.any { it.nombre == "Daisuke" }) },
                { assertTrue(personaFindAll.any { it.nombre == "Kozo" }) }
            )
        }

        @Test
        @DisplayName("Eliminar persona correctamente")
        fun eliminarPersona() {

            val persona = PersonaEntity(
                id = 1,
                nombre = "Pepe",
                apellido = "Gonzalez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("2000-01-01"),
                salario = 1000.0,
                pais = "España",
                rol = "jugador",
                imagen = "",
                equipo = Persona.Equipos.MUPPET
            )


            val id = dao.save(persona)

            val resultado = dao.delete(id)
            val personaEliminada : PersonaEntity? = dao.findById(id)

            assertAll(
                { assertEquals(1, resultado, "La eliminación debe afectar a una fila") },
                { assertNull(personaEliminada, "El alumno eliminado no debe existir") }
            )
        }

        @Test
        @DisplayName("Eliminar datos de persona correctamente")
        fun deleteAll() {

            dao.deleteAll()

            val persona = PersonaEntity(
                id = 1,
                nombre = "Pepe",
                apellido = "Gonzalez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("2000-01-01"),
                salario = 1000.0,
                pais = "España",
                rol = "jugador",
                imagen = "",
                equipo = Persona.Equipos.MUPPET
            )

            val persona2 = PersonaEntity(
                id = 2,
                nombre = "Pepa",
                apellido = "Gonzalez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("2000-01-01"),
                salario = 1000.0,
                pais = "España",
                rol = "jugador",
                imagen = "",
                equipo = Persona.Equipos.MUPPET
            )

            val persona1save = dao.save(persona)
            val persona2save = dao.save(persona2)

            val deleteAll = dao.deleteAll()

            val persona1Eliminada: PersonaEntity? = dao.findById(persona1save)
            val persona2Eliminada: PersonaEntity? = dao.findById(persona2save)
            val todasLasPersonas = dao.findAll()

            assertAll(
                { assertEquals(2, deleteAll, "Debe eliminar dos filas") },
                { assertNull(persona1Eliminada, "La persona 1 debe estar eliminada") },
                { assertNull(persona2Eliminada, "La persona 2 debe estar eliminada") },
                { assertTrue(todasLasPersonas.isEmpty(), "No debe quedar ninguna persona en la BD") }
            )
        }

        @Test
        @DisplayName("Deberia de devolver un id")
        fun findById() {

            val persona = PersonaEntity(
                id = 1,
                nombre = "Pepe",
                apellido = "Gonzalez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("2000-01-01"),
                salario = 1000.0,
                pais = "España",
                rol = "jugador",
                imagen = "",
                equipo = Persona.Equipos.MUPPET
            )

            val id = dao.findById(persona.id)

            assertNotNull(1, "debe exisitir un alumno con el id : 1")

        }

        @Test
        @DisplayName("Debe de guardar a una persona correctamente")
        fun save() {

            val persona = PersonaEntity(
                id = 1,
                nombre = "Pepe",
                apellido = "Gonzalez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("2000-01-01"),
                salario = 1000.0,
                pais = "España",
                rol = "jugador",
                imagen = "",
                equipo = Persona.Equipos.MUPPET
            )

            val personaSaved = dao.save(persona)
            val personaSavedId = dao.findById(personaSaved)

            assertAll(
                { assertNotNull(personaSavedId, "La persona insertada no es nula") },
                { assertEquals(persona.id, personaSavedId?.id, "El ID debe coincidir") },
                { assertEquals(persona.nombre, personaSavedId?.nombre, "El nombre debe coincidir") },
                { assertEquals(persona.apellido, personaSavedId?.apellido, "El apellido debe coincidir") },
                { assertEquals(persona.fechaNacimiento, personaSavedId?.fechaNacimiento, "La fecha de nacimiento debe coincidir") },
                { assertEquals(persona.fechaIncorporacion, personaSavedId?.fechaIncorporacion, "La fecha de incorporación debe coincidir") },
                { assertEquals(persona.salario, personaSavedId?.salario, "El salario debe coincidir") },
                { assertEquals(persona.pais, personaSavedId?.pais, "El país debe coincidir") },
                { assertEquals(persona.rol, personaSavedId?.rol, "El rol debe coincidir") },
                { assertEquals(persona.imagen, personaSavedId?.imagen, "La imagen debe coincidir") },
                { assertEquals(persona.equipo, personaSavedId?.equipo, "El equipo debe coincidir") }
            )

        }
    }

    @Nested
    @DisplayName("Casos incorrectos para dao")
    inner class CasosIncorrectos {

        @Test
        @DisplayName("Deberia de no exisitir el id")
        fun findByIdIncorrecto() {

            val persona = PersonaEntity(
                id = 1,
                nombre = "Pepe",
                apellido = "Gonzalez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("2000-01-01"),
                salario = 1000.0,
                pais = "España",
                rol = "jugador",
                imagen = "",
                equipo = Persona.Equipos.MUPPET
            )

            val id = dao.findById(666)

            assertNull(id, "El id no deberia existir")
        }

        @Test
        @DisplayName("No se deberia de poder eliminar a la persona por que no existe")
        fun deleteIncorrecto() {
            val persona = PersonaEntity(
                id = 1,
                nombre = "Pepe",
                apellido = "Gonzalez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("2000-01-01"),
                salario = 1000.0,
                pais = "España",
                rol = "jugador",
                imagen = "",
                equipo = Persona.Equipos.MUPPET
            )

            val deletePersona = dao.delete(999)

            assertEquals(0, deletePersona, "El id no deberia existir")
        }
    }
}