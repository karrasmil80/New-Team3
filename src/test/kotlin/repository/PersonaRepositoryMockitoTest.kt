package repository

import dev.newteam.newteam3.plantilla.dao.EntrenadorEntity
import dev.newteam.newteam3.plantilla.dao.PersonaDao
import dev.newteam.newteam3.plantilla.mapper.toModel
import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Persona
import dev.newteam.newteam3.plantilla.repositories.PersonaRepositoryImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class PersonaRepositoryMockitoTest {

    @Mock
    private lateinit var dao : PersonaDao

    @InjectMocks
    private lateinit var repository: PersonaRepositoryImpl

    @Nested
    @DisplayName("Casos correctos repositorio")

    inner class CasosCorrectosRepositorio {
        @Test
        @DisplayName("Deberia de devolver una lista con todos los miembros")
        fun findAll() {
            val entrenadorEntity = EntrenadorEntity(
                id = 1,
                nombre = "Pedro",
                apellido = "Gutierrez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("2006-09-08"),
                salario = 1000.0,
                pais = "España",
                rol = "entrenador",
                imagen = "",
                equipo = Persona.Equipos.MUPPET,
                especializacion = Entrenador.Especializacion.PORTEROS
            )

            whenever(dao.findAll()).thenReturn(listOf(entrenadorEntity))

            val entrandorLista = repository.findAll()

            assertAll(
                { assertNotNull(entrandorLista.size == 1, "Entrenador no nulo") },
                { assertEquals(1, entrandorLista.size, "Entrenador correcto") },
                { assertEquals(1, entrandorLista[0].id, "Casilla id correcta") },
                { assertEquals("Pedro", entrandorLista[0].nombre, "Casilla nombre correcta") },
                { assertEquals("Gutierrez", entrandorLista[0].apellido, "Casilla apellidos correcta") },
                { assertEquals(LocalDate.parse("1970-01-01"), entrandorLista[0].fechaNacimiento, "Casilla fecha nacimiento correcta") },
                { assertEquals(LocalDate.parse("2006-09-08"), entrandorLista[0].fechaIncorporacion, "Casilla fecha incorporacion correcta") },
                { assertEquals(1000.0, entrandorLista[0].salario, "Casilla salario correcta") },
                { assertEquals("España", entrandorLista[0].pais, "Casilla pais correcta") },
                { assertEquals("", entrandorLista[0].rol, "Casilla rol correcta") },
                { assertEquals(Entrenador.Especializacion.PORTEROS, entrenadorEntity.especializacion, "Casilla especialidad correcta") },
                { assertEquals("", entrenadorEntity.imagen, "Casilla ruta imagen correcta") },
            )
            verify(dao, atLeastOnce()).findAll()
        }

        @Test
        @DisplayName("Deberia de devolver un miembro buscado por id")
        fun findById() {
            val entrenadorEntity = EntrenadorEntity(
                id = 2,
                nombre = "Pedro",
                apellido = "Gutierrez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("1990-01-01"),
                salario = 1000.0,
                pais = "España",
                imagen = "",
                rol = "",
                equipo = Persona.Equipos.MUPPET,
                especializacion = Entrenador.Especializacion.PORTEROS
            )

            whenever(dao.findById(2)).thenReturn(entrenadorEntity)

            val entrenadorId = repository.findById(2)

            assertAll(
                { assertNotNull(entrenadorId, "Entrenador no nulo") },
                { assertEquals(2, entrenadorId!!.id, "Casilla id correcta") },
                { assertEquals("Pedro", entrenadorId!!.nombre, "Casilla nombre correcta") },
                { assertEquals("Gutierrez", entrenadorId!!.apellido, "Casilla apellidos correcta") },
                { assertEquals(LocalDate.parse("1970-01-01"), entrenadorId!!.fechaNacimiento, "Casilla fecha nacimiento correcta") },
                { assertEquals(LocalDate.parse("1990-01-01"), entrenadorId!!.fechaIncorporacion, "Casilla fecha incorporacion correcta") },
                { assertEquals(1000.0, entrenadorId!!.salario, "Casilla salario correcta") },
                { assertEquals("España", entrenadorId!!.pais, "Casilla pais correcta") },
                { assertEquals("", entrenadorId!!.imagen, "Casilla ruta imagen correcta") },
                { assertEquals("", entrenadorId!!.rol, "Casilla rol correcta") },
            )
        }

        @Test
        @DisplayName("Debería salvar un miembro y devolver el objeto guardado")
        fun save() {
            val entrenadorEntity = EntrenadorEntity(
                id = 2,
                nombre = "Pedro",
                apellido = "Gutierrez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("1990-01-01"),
                salario = 1000.0,
                pais = "España",
                rol = "entrenador",
                imagen = "",
                equipo = Persona.Equipos.MUPPET,
                especializacion = Entrenador.Especializacion.PORTEROS
            )

            // Mockeamos que el dao.save devuelve un id (por ejemplo 2)
            whenever(dao.save(any())).thenReturn(2)

            // Mockeamos el guardado específico del entrenador
            whenever(dao.saveEntrenador(entrenadorEntity)).thenReturn(1) // si devuelve algo, por ejemplo filas afectadas

            // Llamamos al método save del repositorio, que ahora devuelve el objeto guardado
            val entrenadorSave = repository.save(entrenadorEntity.toModel()) as Entrenador

            assertAll(
                { assertNotNull(entrenadorSave, "Entrenador no nulo") },
                { assertEquals(2, entrenadorSave.id, "Casilla id correcta") },
                { assertEquals("Pedro", entrenadorSave.nombre, "Casilla nombre correcta") },
                { assertEquals("Gutierrez", entrenadorSave.apellido, "Casilla apellido correcta") },
                { assertEquals(LocalDate.parse("1970-01-01"), entrenadorSave.fechaNacimiento, "Casilla fecha nacimiento correcta") },
                { assertEquals(LocalDate.parse("1990-01-01"), entrenadorSave.fechaIncorporacion, "Casilla fecha incorporacion correcta") },
                { assertEquals(1000.0, entrenadorSave.salario, "Casilla salario correcta") },
                { assertEquals("España", entrenadorSave.pais, "Casilla pais correcta") },
                { assertEquals("entrenador", entrenadorSave.rol, "Casilla rol correcta") },
                { assertEquals(Entrenador.Especializacion.PORTEROS, entrenadorSave.especializacion, "Casilla especialización correcta") }
            )

            verify(dao).save(any())
            verify(dao).saveEntrenador(any())
        }

        @Test
        @DisplayName("Deberia de borrar un id")
        fun delete() {
            val entrenadorEntity = EntrenadorEntity(
                id = 2,
                nombre = "Pedro",
                apellido = "Gutierrez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("1990-01-01"),
                salario = 1000.0,
                pais = "España",
                rol = "entrenador",
                imagen = "",
                equipo = Persona.Equipos.MUPPET,
                especializacion = Entrenador.Especializacion.PORTEROS
            )

            whenever(dao.findById(2)).thenReturn(entrenadorEntity)
            whenever(dao.delete(2)).thenReturn(1)

            repository.deleteById(2)

            verify(dao).findById(2)
            verify(dao).delete(2)
        }

        @Test
        @DisplayName("Debería eliminar todos los datos de los miembros")
        fun deleteAll() {

            val entrenadorEntity = EntrenadorEntity(
                id = 2,
                nombre = "Pedro",
                apellido = "Gutierrez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("1990-01-01"),
                salario = 1000.0,
                pais = "España",
                rol = "entrenador",
                imagen = "",
                equipo = Persona.Equipos.MUPPET,
                especializacion = Entrenador.Especializacion.PORTEROS
            )

            repository.deleteAll()
            verify(dao, times(1)).deleteAll()
        }

        @Test
        @DisplayName("Debería salvar los datos de varios miembros")
        fun saveAll() {
            val entrenadorEntity = EntrenadorEntity(
                id = 1,
                nombre = "Pedro",
                apellido = "Gutierrez",
                fechaNacimiento = LocalDate.parse("1970-01-01"),
                fechaIncorporacion = LocalDate.parse("1990-01-01"),
                salario = 1000.0,
                pais = "España",
                rol = "entrenador",
                imagen = "",
                equipo = Persona.Equipos.MUPPET,
                especializacion = Entrenador.Especializacion.PORTEROS
            )

            val listaModel = listOf(entrenadorEntity.toModel())

            whenever(dao.save(any())).thenReturn(1)
            whenever(dao.saveEntrenador(any())).thenReturn(1)

            repository.saveAll(listaModel)

            // Verificamos que se haya llamado dao.save y dao.saveEntrenador para cada entidad
            verify(dao, times(listaModel.size)).save(any())
            verify(dao, times(listaModel.size)).saveEntrenador(any())
        }

        @Nested
        @DisplayName("CasosIncorrectos")
        inner class CasosIncorrectos {
            @Test
            @DisplayName("Deberia devolver una lista vacia")
            fun findAllIncorrecto() {

                whenever(dao.findAll()).thenReturn(emptyList())

                val emptyList = repository.findAll()

                assertEquals(0, emptyList.size, "La lista debe estar vacía")

                verify(dao, times(1)).findAll()
            }
        }

        @Test
        @DisplayName("Deberia de de ser un id nulo")
        fun findByIdIncorrecto() {

            whenever(dao.findById(1)).thenReturn(null)

            val idNulo = repository.findById(1)

            assertEquals(null, idNulo, "El id deberia de ser nulo")

            verify(dao, times(1)).findById(1)
        }

        @Test
        @DisplayName("Deberia de no encontrar un alumno por identificador")
        fun deleteByIdIncorrecto() {


            whenever(dao.findById(1)).thenReturn(null)
            whenever(dao.delete(any())).thenReturn(1)
            whenever(dao.deleteEntrenador(any())).thenReturn(1)

            repository.deleteById(1)

            verify(dao, times(1)).findById(1)
        }

    }
}