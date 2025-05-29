package convocatoria.dao

import dev.newteam.newteam3.convocatoria.dao.ConvocatoriaDao
import dev.newteam.newteam3.convocatoria.dao.ConvocatoriaEntity
import dev.newteam.newteam3.convocatoria.utils.provideConvocatoriaDao
import dev.newteam.newteam3.database.JdbiManager
import dev.newteam.newteam3.plantilla.dao.PersonaDao
import dev.newteam.newteam3.plantilla.utils.providePersonaDao
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertTrue
import java.time.LocalDateTime


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConvocatoriaDaoTest {

    private lateinit var jdbi: JdbiManager
    private lateinit var convocatoriaDao: ConvocatoriaDao


    @BeforeAll
    fun setUp() {
        // Inicializamos la BD
        val jdbi = JdbiManager(
            databaseUrl = "jdbc:h2:mem:newteamDB;DB_CLOSE_DELAY=-1",
            databaseInitTables = true,
            databaseInitData = true,
            databaseLogger = false
        ).jdbi
        convocatoriaDao = provideConvocatoriaDao(jdbi)
    }

    @Nested
    @DisplayName("Casos correctos para convocatoria")
    inner class CasosCorrectos {

        @Test
        @DisplayName("Debería devolver una lista de convocatorias")
        fun findAll() {
            val convocatoria = ConvocatoriaEntity(
                id = 1,
                jornada = LocalDateTime.parse("1970-01-01T00:00:00"),
                descripcion = "Jornada de apertura",
                jugadoresConvocados = emptyList(),
            )

            val convocatorias = convocatoriaDao.findAll()

            assertAll(
                { assertTrue(convocatorias.isNotEmpty(), "La lista no debe estar vacía") },
                { assertTrue(convocatorias.any { it.descripcion == "Jornada de apertura" }, "Debe contener la convocatoria guardada") }
            )
        }
    }
}