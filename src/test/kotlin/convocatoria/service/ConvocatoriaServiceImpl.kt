package convocatoria.service

import com.github.michaelbull.result.Ok
import dev.newteam.newteam3.convocatoria.models.Convocatoria
import dev.newteam.newteam3.convocatoria.repositories.ConvocatoriaRepository
import dev.newteam.newteam3.convocatoria.service.ConvocatoriaService
import dev.newteam.newteam3.convocatoria.service.ConvocatoriaServiceImpl
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class ConvocatoriaServiceImpl {

    @Mock
    private lateinit var repository: ConvocatoriaRepository

    @InjectMocks
    private lateinit var service: ConvocatoriaServiceImpl

    @Nested
    @DisplayName("Casos correctos")
    inner class CasosCorrectos {

        @Test
        @DisplayName("Debería devolver todas las convocatorias")
        fun findAll() {

            val convocatoria = Convocatoria(
                id = 1,
                jornada = LocalDate.of(1970, 1, 1),
                personalList = emptyList()
            )

            whenever(repository.findAll()).thenReturn(listOf(convocatoria))

            val result = service.findAll()

            assertTrue(result.isOk)
            verify(repository, times(1)).findAll()
        }

        @Test
        @DisplayName("Debería devolver una convocatoria por su ID")
        fun findById() {

            val convocatoria = Convocatoria(
                id = 1,
                jornada = LocalDate.of(1970, 1, 1),
                personalList = emptyList()
            )

            whenever(repository.findById(1)).thenReturn(convocatoria)

            val result = service.findById(1)

            assertTrue(result.isOk)

            verify(repository, times(1)).findById(1)
        }

        @Test
        @DisplayName("Debería guardar una convocatoria")
        fun save() {

            val convocatoria = Convocatoria(
                id = 1,
                jornada = LocalDate.of(1970, 1, 1),
                personalList = emptyList()
            )

            whenever(repository.save(convocatoria)).thenReturn(convocatoria)

            val result = service.save(convocatoria)

            assertTrue(result.isOk)
            verify(repository, times(1)).save(convocatoria)
        }

        @Test
        @DisplayName("Debería eliminar una convocatoria por ID")
        fun deleteById() {

            val convocatoria = Convocatoria(
                id = 1,
                jornada = LocalDate.of(1970, 1, 1),
                personalList = emptyList()
            )

            whenever(repository.deleteById(1)).thenReturn(1)

            val result = service.deleteById(1)

            assertTrue(result.isOk)
            verify(repository, times(1)).deleteById(1)
        }

        @Test
        @DisplayName("Debería eliminar todas las convocatorias")
        fun deleteAll() {

            val convocatoria = Convocatoria(
                id = 1,
                jornada = LocalDate.of(1970, 1, 1),
                personalList = emptyList()
            )

            whenever(repository.deleteAll()).thenReturn(10)

            val result = service.deleteAll()

            assertTrue(result.isOk)
            verify(repository, times(1)).deleteAll()
        }

        @Test
        @DisplayName("Debería guardar todas las convocatorias")
        fun saveAll() {

            val convocatoria = Convocatoria(
                id = 1,
                jornada = LocalDate.of(1970, 1, 1),
                personalList = emptyList()
            )

            val lista = listOf(convocatoria)
            whenever(repository.saveAll(lista)).thenReturn(lista)

            val result = service.saveAll(lista)

            assertTrue(result.isOk)
            verify(repository, times(1)).saveAll(lista)
        }
    }
}