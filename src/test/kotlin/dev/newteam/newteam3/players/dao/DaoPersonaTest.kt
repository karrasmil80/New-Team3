package dev.newteam.newteam3.players.dao

import dev.newteam.newteam3.database.JdbiManager
import dev.newteam.newteam3.plantilla.dao.PersonaDao
import dev.newteam.newteam3.plantilla.dao.PersonaEntity
import dev.newteam.newteam3.plantilla.utils.providePersonaDao
import org.jdbi.v3.core.Jdbi
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import java.time.LocalDate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DaoPersonaTest {

    private lateinit var jdbi : JdbiManager
    private lateinit var dao : PersonaDao

    @BeforeEach
    fun setUp() {
        val jdbi = JdbiManager(
            databaseUrl = "jdbc:h2:mem:newteamDB;DB_CLOSE_DELAY=-1",
            databaseInitData = true,
            databaseInitTables = true,
            databaseLogger = true
        ).jdbi
        dao = providePersonaDao(jdbi)
    }

    @AfterEach
    fun tearDown() {
        dao.deleteAll()
    }

    @Nested
    @DisplayName("Casos correctos DAO")
    inner class CasosCorrectos {
        fun save() {
            val persona = PersonaEntity(
                id = 1,
                nombre = "Pepe",
                apellido = "Gonzalez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("2007-12-03"),
                salario = 1000.0,
                pais = "España",
                rol = "",
                imagen = ""
            )


        }
    }


}