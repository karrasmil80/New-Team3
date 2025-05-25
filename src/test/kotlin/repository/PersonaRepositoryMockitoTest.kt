package repository

import dev.newteam.newteam3.plantilla.dao.PersonaDao
import dev.newteam.newteam3.plantilla.dao.PersonaEntity
import dev.newteam.newteam3.plantilla.mapper.toModel
import dev.newteam.newteam3.plantilla.models.Persona
import dev.newteam.newteam3.plantilla.repositories.PersonaRepository
import dev.newteam.newteam3.plantilla.repositories.PersonaRepositoryImpl
import dev.newteam.newteam3.plantilla.service.PersonaService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
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
        @DisplayName("Deberia de devolver una lista de los miembros de la plantilla")
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
            ).toModel()


        }
    }
}