package convocatoria.dao

import dev.newteam.newteam3.convocatoria.dao.ConvocatoriaDao
import dev.newteam.newteam3.convocatoria.dao.JugadorConvocadoDao
import dev.newteam.newteam3.convocatoria.dao.JugadorConvocadoEntity
import dev.newteam.newteam3.convocatoria.utils.provideConvocatoriaDao
import dev.newteam.newteam3.convocatoria.utils.provideJugadorConvocadoDao
import dev.newteam.newteam3.database.JdbiManager
import org.jdbi.v3.core.Jdbi
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

/*
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JugadorConvocadoDaoTest {

    private lateinit var jdbi : Jdbi
    private lateinit var jugadorconvocatoria : JugadorConvocadoDao

    @BeforeAll
    fun setUp() {
        val jdbi = JdbiManager(
            databaseUrl = "jdbc:h2:mem:newteamDB;DB_CLOSE_DELAY=-1",
            databaseInitTables = true,
            databaseInitData = true,
            databaseLogger = false
        ).jdbi
        jugadorconvocatoria = provideJugadorConvocadoDao(jdbi)
    }

    @BeforeEach
    fun clear() {
        jugadorconvocatoria.deleteAll()
    }

    @Test
    fun findById() {
        val entity = JugadorConvocadoEntity("1", 37, 1)
        val save = jugadorconvocatoria.save(entity)
        assertEquals(1, save)

        val found = jugadorconvocatoria.findById("1")
        assertNotNull(found)
        assertEquals(entity, found)
    }

    @Test
    fun findAll() {
        val lista = listOf(
            JugadorConvocadoEntity("1", 37, 1),
            JugadorConvocadoEntity("2", 38, 2)
        )
        lista.forEach { jugadorconvocatoria.save(it) }

        val all = jugadorconvocatoria.findAll()

        assertEquals(2, all.size)

    }

    @Test
    fun findByConvocatoriaId() {
        jugadorconvocatoria.save(JugadorConvocadoEntity("1", 37, 3))
        jugadorconvocatoria.save(JugadorConvocadoEntity("2", 38, 3))
        jugadorconvocatoria.save(JugadorConvocadoEntity("3", 37, 2))

        val result = jugadorconvocatoria.findByConvocatoriaId(3)
        assertEquals(2, result.size)
    }

    @Test
    fun deleteByConvocatoriaId() {
        jugadorconvocatoria.save(JugadorConvocadoEntity("11", 37, 2))
        jugadorconvocatoria.save(JugadorConvocadoEntity("12", 38, 2))
        jugadorconvocatoria.save(JugadorConvocadoEntity("13", 37, 3))

        val deleted = jugadorconvocatoria.deleteByConvocatoriaId(2)
        assertEquals(2, deleted)

        val jugadorDeleteId = jugadorconvocatoria.findAll()
        assertEquals(1, jugadorDeleteId.size)
        assertEquals("13", jugadorDeleteId[0].id)
    }

    @Test
    fun deleteAll() {
        jugadorconvocatoria.save(JugadorConvocadoEntity("15", 37, 1))
        jugadorconvocatoria.save(JugadorConvocadoEntity("16", 38, 2))

        val deleted = jugadorconvocatoria.deleteAll()
        assertEquals(2, deleted)

        val jugadorFindAll = jugadorconvocatoria.findAll()
        assertTrue(jugadorFindAll.isEmpty())
    }




}

 */