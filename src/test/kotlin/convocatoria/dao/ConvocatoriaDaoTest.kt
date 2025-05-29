package convocatoria.dao

import dev.newteam.newteam3.convocatoria.dao.ConvocatoriaDao
import dev.newteam.newteam3.convocatoria.dao.ConvocatoriaEntity
import dev.newteam.newteam3.convocatoria.dao.JugadorConvocadoEntity
import dev.newteam.newteam3.convocatoria.utils.provideConvocatoriaDao
import dev.newteam.newteam3.database.JdbiManager
import dev.newteam.newteam3.plantilla.dao.PersonaDao
import dev.newteam.newteam3.plantilla.utils.providePersonaDao
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.*
import java.time.LocalDateTime
import org.jdbi.v3.core.Jdbi
import org.junit.jupiter.api.*
import java.time.LocalDate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConvocatoriaDaoTest {

    private lateinit var jdbi: Jdbi
    private lateinit var convocatoriaDao: ConvocatoriaDao

    @BeforeAll
    fun setUp() {
        val jdbi = JdbiManager(
            databaseUrl = "jdbc:h2:mem:newteamDB;DB_CLOSE_DELAY=-1",
            databaseInitTables = true,
            databaseInitData = true,
            databaseLogger = false
        ).jdbi
        convocatoriaDao = provideConvocatoriaDao(jdbi)
    }

    //Por si se acumulan datos
    @BeforeEach
    fun clear() {
        convocatoriaDao.deleteAll()
    }

    @Nested
    @DisplayName("Casos correctos para convocatoria")
    inner class CasosCorrectos {

        @Test
        fun testFindAll() {
            val datos1 = ConvocatoriaEntity(0, LocalDate.now())
            val datos2 = ConvocatoriaEntity(0, LocalDate.now().plusDays(1))
            convocatoriaDao.save(datos1)
            convocatoriaDao.save(datos2)

            val lista = convocatoriaDao.findAll()
            assertTrue(lista.size >= 2, "Debe haber al menos dos convocatorias")
        }

        @Test
        fun findById() {
            val convocatoria = ConvocatoriaEntity(1, LocalDate.of(2025, 5, 29))
            val id = convocatoriaDao.save(convocatoria)
            val saved = convocatoriaDao.findById(id)

            assertEquals(id, saved!!.id)
            assertEquals(LocalDate.of(2025, 5, 29), saved!!.jornada)
        }

        @Test
        fun findByJornada() {
            val fecha = LocalDate.of(2025, 5, 30)
            convocatoriaDao.save(ConvocatoriaEntity(0, fecha))

            val save = convocatoriaDao.findByJornada(fecha)
            assertNotNull(save)
            assertEquals(fecha, save!!.jornada)
        }

        @Test
        fun deleteById() {
            val convocatoria = ConvocatoriaEntity(0, LocalDate.of(2025, 5, 29))
            val id = convocatoriaDao.save(convocatoria)

            val idDelete = convocatoriaDao.deleteById(id)
            assertEquals(1, idDelete)

            assertNull(convocatoriaDao.findById(id))
        }

    }
}
