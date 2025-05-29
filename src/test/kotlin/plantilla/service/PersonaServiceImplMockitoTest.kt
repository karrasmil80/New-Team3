package plantilla.service

import com.github.benmanes.caffeine.cache.Cache
import dev.newteam.newteam3.plantilla.dao.EntrenadorEntity
import dev.newteam.newteam3.plantilla.mapper.toModel
import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Persona
import dev.newteam.newteam3.plantilla.repositories.PersonaRepositoryImpl
import dev.newteam.newteam3.plantilla.service.PersonaServiceImpl
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class PersonaServiceImplMockitoTest {

    @Mock
    private lateinit var repository: PersonaRepositoryImpl

    @Mock
    private lateinit var cache: Cache<Int, Persona>

    @InjectMocks
    private lateinit var service: PersonaServiceImpl

    @Nested
    @DisplayName("Casos correctos")
    inner class CasosCorrectos {

        @Test
        @DisplayName("Deberia de devolver una lista de la plantilla")
        fun findAll() {
            val entrenadorEntity = EntrenadorEntity(
                id = 1,
                nombre = "Pedro",
                apellido = "Gutierrez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("2006-09-08"),
                salario = 1000.0,
                pais = "España",
                rol = "Entrenador",
                especializacion = Entrenador.Especializacion.PORTEROS,
                imagen = "",
                equipo = Persona.Equipos.MUPPET
            )

            val model = entrenadorEntity.toModel()

            whenever(repository.findAll()).thenReturn(listOf(model))

            val entrandorLista = service.findAll()

            assertTrue(entrandorLista.isOk, "El resultado deberia de ser una lista")

            verify(repository, times(1)).findAll()

        }

        @Test
        @DisplayName("Debería devolver el identificador de un miembro")
        fun findById() {
            val entrenadorEntity = EntrenadorEntity(
                id = 1,
                nombre = "Pedro",
                apellido = "Gutierrez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("2006-09-08"),
                salario = 1000.0,
                pais = "España",
                rol = "entrenador",
                especializacion = Entrenador.Especializacion.PORTEROS,
                imagen = "",
                equipo = Persona.Equipos.MUPPET
            )

            // mockea el repositorio para que devuelva la entidad
            whenever(repository.findById(1)).thenReturn(entrenadorEntity.toModel())

            val result = service.findById(1)

            val mockPersona = repository.findById(1)
            println(mockPersona)
            println(mockPersona?.rol)


            assertTrue(result.isOk)


            verify(repository, times(1)).findById(1)
        }

        @Test
        @DisplayName("Deberia de guardar un objeto")
        fun save() {

            val entrenadorEntity = EntrenadorEntity(
                id = 1,
                nombre = "Pedro",
                apellido = "Gutierrez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("2006-09-08"),
                salario = 1000.0,
                pais = "España",
                rol = "entrenador",
                especializacion = Entrenador.Especializacion.PORTEROS,
                imagen = "",
                equipo = Persona.Equipos.MUPPET
            )

            val personaModel = entrenadorEntity.toModel()

            whenever(repository.save(personaModel)).thenReturn(personaModel)

            val result = service.save(personaModel)

            verify(repository).save(personaModel)
            verify(cache).put(personaModel.id, personaModel)


        }



        @Test
        @DisplayName("Debería eliminar el identificador de un miembro")
        fun deleteById() {

            val entrenadorEntity = EntrenadorEntity(
                id = 1,
                nombre = "Pedro",
                apellido = "Gutierrez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("2006-09-08"),
                salario = 1000.0,
                pais = "España",
                rol = "Entrenador",
                especializacion = Entrenador.Especializacion.PORTEROS,
                imagen = "",
                equipo = Persona.Equipos.MUPPET
            )

            whenever(repository.deleteById(1)).thenReturn(1)

            doNothing().whenever(cache).invalidate(1)

            val resultado = service.deleteById(1)

            assertTrue(resultado.isOk)

            verify(repository, times(1)).deleteById(1)
            verify(cache, times(1)).invalidate(1)
        }

        @Test
        @DisplayName("Debería borrar todos los miembros")
        fun deleteAll() {

            val entrenadorEntity = EntrenadorEntity(
                id = 1,
                nombre = "Pedro",
                apellido = "Gutierrez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("2006-09-08"),
                salario = 1000.0,
                pais = "España",
                rol = "Entrenador",
                especializacion = Entrenador.Especializacion.PORTEROS,
                imagen = "",
                equipo = Persona.Equipos.MUPPET
            )

            whenever(repository.deleteAll()).thenReturn(10)

            doNothing().whenever(cache).invalidateAll()

            val resultado = service.deleteAll()

            assertTrue(resultado.isOk)

            verify(repository, times(1)).deleteAll()
            verify(cache, times(1)).invalidateAll()
        }

        @Test
        @DisplayName("Debería guardar todos los miembros")
        fun saveAll() {
            val plantillaList = listOf(
                EntrenadorEntity(
                    id = 1,
                    nombre = "Pedro",
                    apellido = "Gutierrez",
                    fechaNacimiento = LocalDate.parse("1970-01-01"),
                    fechaIncorporacion = LocalDate.parse("2006-09-08"),
                    salario = 1000.0,
                    pais = "España",
                    rol = "Entrenador",
                    especializacion = Entrenador.Especializacion.PORTEROS,
                    imagen = "",
                    equipo = Persona.Equipos.MUPPET
                ).toModel()
            )

            val personaModelList = plantillaList

            whenever(repository.saveAll(personaModelList)).thenReturn(personaModelList)

            doNothing().whenever(cache).invalidateAll()

            val resultado = service.saveAll(personaModelList)

            assertTrue(resultado.isOk)
            verify(repository, times(1)).saveAll(personaModelList)
            verify(cache, times(1)).invalidateAll()
        }
    }
}