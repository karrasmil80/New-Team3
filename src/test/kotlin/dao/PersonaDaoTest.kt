package dao

import dev.newteam.newteam3.database.JdbiManager
import dev.newteam.newteam3.plantilla.dao.PersonaDao
import dev.newteam.newteam3.plantilla.dao.PersonaEntity
import dev.newteam.newteam3.plantilla.utils.providePersonaDao
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.Assertions.*
import java.time.LocalDate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonaDaoTest {

    private lateinit var jdbi : JdbiManager
    private lateinit var dao : PersonaDao

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

    @AfterEach
    fun tearDown() {
        dao.deleteAll()
    }

    @Test
    fun findAll () {
        val persona = PersonaEntity(
            id = 1,
            nombre = "Pepe",
            apellido = "Gonzalez",
            fechaNacimiento = LocalDate.parse("1970-01-01"),
            fechaIncorporacion = LocalDate.parse("2000-01-01"),
            salario = 1000.0,
            pais = "España",
            rol = "jugador",
            imagen = ""
        )

        val personaFindAll = dao.findAll()

        assertAll(
            { assertEquals(4, personaFindAll.size, "La lista debe de contener personas") },
            { assertTrue(personaFindAll.any { it.nombre == "Daisuke"}) },
            { assertTrue(personaFindAll.any { it.nombre == "Kozo"}) }
        )
    }
}