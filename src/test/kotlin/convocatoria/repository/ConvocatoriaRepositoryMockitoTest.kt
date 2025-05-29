package convocatoria.repository

import dev.newteam.newteam3.convocatoria.dao.ConvocatoriaDao
import dev.newteam.newteam3.convocatoria.dao.ConvocatoriaEntity
import dev.newteam.newteam3.convocatoria.dao.JugadorConvocadoDao
import dev.newteam.newteam3.convocatoria.dao.JugadorConvocadoEntity
import dev.newteam.newteam3.convocatoria.models.Convocatoria
import dev.newteam.newteam3.convocatoria.models.JugadorConvocado
import dev.newteam.newteam3.convocatoria.repositories.ConvocatoriaRepository
import dev.newteam.newteam3.convocatoria.repositories.ConvocatoriaRepositoryImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.util.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.mockito.Mockito.mock
import org.mockito.kotlin.*

@ExtendWith(MockitoExtension::class)
class ConvocatoriaRepositoryMockitoTest {

    private lateinit var convocatoriaDao : ConvocatoriaDao
    private lateinit var jugadorConvocadoDao: JugadorConvocadoDao
    private lateinit var repository : ConvocatoriaRepository

    @BeforeEach
    fun setup() {
        convocatoriaDao = mock()
        jugadorConvocadoDao = mock()
        repository = ConvocatoriaRepositoryImpl(jugadorConvocadoDao, convocatoriaDao)
    }

    @Test
    fun save() {
        val convocado = JugadorConvocado(UUID.randomUUID(), 0, 10)
        val convocatoria = Convocatoria(0, LocalDate.now(), listOf(convocado))
        val savedId = 1

        whenever(convocatoriaDao.save(any())).thenReturn(savedId)
        whenever(jugadorConvocadoDao.save(any())).thenReturn(1)

        val result = repository.save(convocatoria)

        verify(convocatoriaDao).save(any())
        verify(jugadorConvocadoDao).save(any())

        assertEquals(savedId, result.id)
        assertEquals(convocatoria.jornada, result.jornada)
        assertEquals(convocatoria.personalList, result.personalList)
    }

    @Test
    @DisplayName("Debería devolver una lista con todas las convocatorias y sus convocados")
    fun findAll() {
        val convocatoriaEntity = ConvocatoriaEntity(id = 1, jornada = LocalDate.now())
        val jugadorConvocadoEntity = JugadorConvocadoEntity(
            id = UUID.randomUUID().toString(),
            convocatoriaId = 1,
            personaId = 100
        )

        whenever(convocatoriaDao.findAll()).thenReturn(listOf(convocatoriaEntity))
        whenever(jugadorConvocadoDao.findByConvocatoriaId(1)).thenReturn(listOf(jugadorConvocadoEntity))

        val result = repository.findAll()

        assertAll(
            { assertNotNull(result, "La lista no debe ser nula") },
            { assertEquals(1, result.size, "Debe devolver una convocatoria") },
            { assertEquals(1, result[0].id, "ID convocatoria correcto") },
            { assertEquals(LocalDate.now(), result[0].jornada, "Jornada correcta") },
            { assertEquals(1, result[0].personalList.size, "Debe contener un jugador convocado") },
            { assertEquals(UUID.fromString(jugadorConvocadoEntity.id), result[0].personalList[0].id, "ID jugador convocado correcto") },
            { assertEquals(jugadorConvocadoEntity.convocatoriaId, result[0].personalList[0].convocatoriaId, "ConvocatoriaId jugador correcto") },
            { assertEquals(jugadorConvocadoEntity.personaId, result[0].personalList[0].personaId, "PersonaId jugador correcto") }
        )

        verify(convocatoriaDao, atLeastOnce()).findAll()
        verify(jugadorConvocadoDao, atLeastOnce()).findByConvocatoriaId(1)
    }

    @Test
    @DisplayName("Deberia devolver el identificador de un miembro")
    fun findById() {
        val convocatoriaEntity = ConvocatoriaEntity(id = 2, jornada = LocalDate.now())
        val jugadorConvocadoEntity = JugadorConvocadoEntity(
            id = UUID.randomUUID().toString(),
            convocatoriaId = 2,
            personaId = 200
        )

        whenever(convocatoriaDao.findById(2)).thenReturn(convocatoriaEntity)
        whenever(jugadorConvocadoDao.findByConvocatoriaId(2)).thenReturn(listOf(jugadorConvocadoEntity))

        val result = repository.findById(2)

        assertNotNull(result, "La convocatoria no debe ser nula")
        assertAll(
            { assertEquals(2, result!!.id, "ID convocatoria correcto") },
            { assertEquals(LocalDate.now(), result!!.jornada, "Jornada correcta") },
            { assertEquals(1, result!!.personalList.size, "Debe contener un jugador convocado") },
            { assertEquals(UUID.fromString(jugadorConvocadoEntity.id), result!!.personalList[0].id, "ID jugador convocado correcto") },
            { assertEquals(jugadorConvocadoEntity.convocatoriaId, result!!.personalList[0].convocatoriaId, "ConvocatoriaId jugador correcto") },
            { assertEquals(jugadorConvocadoEntity.personaId, result!!.personalList[0].personaId, "PersonaId jugador correcto") }
        )

        verify(convocatoriaDao).findById(2)
        verify(jugadorConvocadoDao).findByConvocatoriaId(2)
    }

    @Test
    @DisplayName("Debería eliminar todas las convocatorias")
    fun deleteAll() {
        whenever(convocatoriaDao.deleteAll()).thenReturn(2)

        val deleted = repository.deleteAll()

        assertEquals(2, deleted, "Debe devolver el número correcto de filas eliminadas")
        verify(convocatoriaDao).deleteAll()
    }

    @Test
    @DisplayName("Debería guardar todas las convocatorias de una lista y devolverlas con IDs actualizados")
    fun saveAll() {
        val convocado1 = JugadorConvocado(UUID.randomUUID(), convocatoriaId = 0, personaId = 101)
        val convocado2 = JugadorConvocado(UUID.randomUUID(), convocatoriaId = 0, personaId = 102)
        val convocado3 = JugadorConvocado(UUID.randomUUID(), convocatoriaId = 0, personaId = 103)

        val convocatoria1 = Convocatoria(
            id = 0,
            jornada = LocalDate.now(),
            personalList = listOf(convocado1)
        )
        val convocatoria2 = Convocatoria(
            id = 0,
            jornada = LocalDate.now(),
            personalList = listOf(convocado2, convocado3)
        )

        val listaConvocatorias = listOf(convocatoria1, convocatoria2)

        whenever(convocatoriaDao.save(any())).thenReturn(101, 102)

        val resultado = repository.saveAll(listaConvocatorias)

        assertAll(
            { assertEquals(2, resultado.size, "Debe devolver todas las convocatorias guardadas") },
            { assertEquals(101, resultado[0].id, "ID actualizado convocatoria 1") },
            { assertEquals(102, resultado[1].id, "ID actualizado convocatoria 2") }
        )

        verify(convocatoriaDao, times(2)).save(any())
    }
}