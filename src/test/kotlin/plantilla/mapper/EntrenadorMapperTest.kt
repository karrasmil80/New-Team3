package plantilla.mapper

import dev.newteam.newteam3.plantilla.dao.EntrenadorEntity
import dev.newteam.newteam3.plantilla.dto.EntrenadorDto
import dev.newteam.newteam3.plantilla.dto.PersonaDto
import dev.newteam.newteam3.plantilla.mapper.toDto
import dev.newteam.newteam3.plantilla.mapper.toEntity
import dev.newteam.newteam3.plantilla.mapper.toModel
import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Persona
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class EntrenadorMapperTest {
    @Test
    fun conversionEntrenadoraPersonaDto() {
        val entrenador = Entrenador(
            id = 1,
            nombre = "Pep",
            apellido = "Guardiola",
            fechaNacimiento = LocalDate.of(1971, 1, 18),
            fechaIncorporacion = LocalDate.of(2008, 6, 1),
            salario = 5000000.0,
            pais = "España",
            imagen = "guardiola.jpg",
            rol = "entrenador",
            especializacion = Entrenador.Especializacion.PORTEROS,
            equipo = Persona.Equipos.MUPPET
        )

        val dto = entrenador.toDto()

        assertTrue(dto is PersonaDto) // ✅ Validamos que la conversión es correcta

        // Validaciones de propiedades
        assertEquals(entrenador.id, dto.id)
        assertEquals(entrenador.nombre, dto.nombre)
        assertEquals(entrenador.apellido, dto.apellido)
        assertEquals(entrenador.fechaNacimiento, dto.fecha_nacimiento)
        assertEquals(entrenador.fechaIncorporacion, dto.fecha_incorporacion)
        assertEquals(entrenador.salario, dto.salario)
        assertEquals(entrenador.pais, dto.pais)
        assertEquals(entrenador.imagen, dto.imagen)
        assertEquals(entrenador.rol, dto.rol)
        assertEquals(entrenador.especializacion, dto.especializacion)
        assertEquals(entrenador.equipo.toString(), dto.equipo)

        // Validamos que las propiedades relacionadas con Jugador sean `null`
        assertNull(dto.posicion)
        assertNull(dto.altura)
        assertNull(dto.dorsal)
        assertNull(dto.peso)
        assertNull(dto.goles)
        assertNull(dto.media_goles)
        assertNull(dto.partidos_jugados)
        assertNull(dto.minutos_jugados)
    }

    @Test
    fun conversionEntrenadorDtoaEntrenador() {
        val entrenadorDto = EntrenadorDto(
            id = 1,
            nombre = "Pep",
            apellido = "Guardiola",
            fechaNacimiento = LocalDate.of(1971, 1, 18),
            fechaIncorporacion = LocalDate.of(2008, 6, 1),
            salario = 5000000.0,
            pais = "España",
            imagen = "entrenador",
            especializacion = Entrenador.Especializacion.PRINCIPAL,
            rol = "entrenador",
            equipo = Persona.Equipos.MUPPET.toString()
        )

        val entrenador = entrenadorDto.toModel()

        assertTrue(entrenador is Entrenador) // ✅ Validamos que la conversión es correcta

        // Validaciones de propiedades
        assertEquals(entrenadorDto.id, entrenador.id)
        assertEquals(entrenadorDto.nombre, entrenador.nombre)
        assertEquals(entrenadorDto.apellido, entrenador.apellido)
        assertEquals(entrenadorDto.fechaNacimiento, entrenador.fechaNacimiento)
        assertEquals(entrenadorDto.fechaIncorporacion, entrenador.fechaIncorporacion)
        assertEquals(entrenadorDto.salario, entrenador.salario)
        assertEquals(entrenadorDto.pais, entrenador.pais)
        assertEquals(entrenadorDto.imagen, entrenador.imagen)
        assertEquals(entrenadorDto.especializacion, entrenador.especializacion)
        assertEquals(entrenadorDto.rol, entrenador.rol)
        assertEquals(Persona.Equipos.valueOf(entrenadorDto.equipo), entrenador.equipo)
    }


    @Test
    fun conversionEntrenadoraEntrenadorEntity() {
        val entrenador = Entrenador(
            id = 1,
            nombre = "Pep",
            apellido = "Guardiola",
            fechaNacimiento = LocalDate.of(1971, 1, 18),
            fechaIncorporacion = LocalDate.of(2008, 6, 1),
            salario = 5000000.0,
            pais = "España",
            imagen = "guardiola.jpg",
            rol = "entrenador",
            especializacion = Entrenador.Especializacion.ASISTENTE,
            equipo = Persona.Equipos.MUPPET
        )

        val entrenadorEntity = entrenador.toEntity()

        assertTrue(entrenadorEntity is EntrenadorEntity) // ✅ Validamos que la conversión es correcta

        // Validaciones de propiedades
        assertEquals(entrenador.id, entrenadorEntity.id)
        assertEquals(entrenador.nombre, entrenadorEntity.nombre)
        assertEquals(entrenador.apellido, entrenadorEntity.apellido)
        assertEquals(entrenador.fechaNacimiento, entrenadorEntity.fechaNacimiento)
        assertEquals(entrenador.fechaIncorporacion, entrenadorEntity.fechaIncorporacion)
        assertEquals(entrenador.salario, entrenadorEntity.salario)
        assertEquals(entrenador.pais, entrenadorEntity.pais)
        assertEquals(entrenador.imagen, entrenadorEntity.imagen)
        assertEquals(entrenador.especializacion, entrenadorEntity.especializacion)
        assertEquals(entrenador.rol, entrenadorEntity.rol)
        assertEquals(entrenador.equipo, entrenadorEntity.equipo)
    }

    @Test
    fun conversionEntrenadorEntityaEntrenador() {
        val entrenadorEntity = EntrenadorEntity(
            id = 1,
            nombre = "Pep",
            apellido = "Guardiola",
            fechaNacimiento = LocalDate.of(1971, 1, 18),
            fechaIncorporacion = LocalDate.of(2008, 6, 1),
            salario = 5000000.0,
            pais = "España",
            imagen = "entrenador",
            especializacion = Entrenador.Especializacion.PORTEROS,
            rol = "entrenador",
            equipo = Persona.Equipos.MUPPET
        )

        val entrenador = entrenadorEntity.toModel()

        assertTrue(entrenador is Entrenador) // ✅ Validamos que la conversión es correcta

        // Validaciones de propiedades
        assertEquals(entrenadorEntity.id, entrenador.id)
        assertEquals(entrenadorEntity.nombre, entrenador.nombre)
        assertEquals(entrenadorEntity.apellido, entrenador.apellido)
        assertEquals(entrenadorEntity.fechaNacimiento, entrenador.fechaNacimiento)
        assertEquals(entrenadorEntity.fechaIncorporacion, entrenador.fechaIncorporacion)
        assertEquals(entrenadorEntity.salario, entrenador.salario)
        assertEquals(entrenadorEntity.pais, entrenador.pais)
        assertEquals(entrenadorEntity.imagen, entrenador.imagen)
        assertEquals(entrenadorEntity.especializacion, entrenador.especializacion)
        assertEquals(entrenadorEntity.rol, entrenador.rol)
        assertEquals(entrenadorEntity.equipo, entrenador.equipo)
    }

    @Test
    fun conversionEntrenadorDtoaEntrenadorEntity() {
        val entrenadorDto = EntrenadorDto(
            id = 1,
            nombre = "Pep",
            apellido = "Guardiola",
            fechaNacimiento = LocalDate.of(1971, 1, 18),
            fechaIncorporacion = LocalDate.of(2008, 6, 1),
            salario = 5000000.0,
            pais = "España",
            imagen = "guardiola.jpg",
            especializacion = Entrenador.Especializacion.PRINCIPAL,
            rol = "entrenador",
            equipo = Persona.Equipos.NEW_TEAM.toString()
        )

        val entrenadorEntity = entrenadorDto.toEntity()

        assertTrue(entrenadorEntity is EntrenadorEntity) // ✅ Validamos que la conversión es correcta

        // Validaciones de propiedades
        assertEquals(entrenadorDto.id, entrenadorEntity.id)
        assertEquals(entrenadorDto.nombre, entrenadorEntity.nombre)
        assertEquals(entrenadorDto.apellido, entrenadorEntity.apellido)
        assertEquals(entrenadorDto.fechaNacimiento, entrenadorEntity.fechaNacimiento)
        assertEquals(entrenadorDto.fechaIncorporacion, entrenadorEntity.fechaIncorporacion)
        assertEquals(entrenadorDto.salario, entrenadorEntity.salario)
        assertEquals(entrenadorDto.pais, entrenadorEntity.pais)
        assertEquals(entrenadorDto.imagen, entrenadorEntity.imagen)
        assertEquals(entrenadorDto.especializacion, entrenadorEntity.especializacion)
        assertEquals(entrenadorDto.rol, entrenadorEntity.rol)
        assertEquals(Persona.Equipos.valueOf(entrenadorDto.equipo), entrenadorEntity.equipo)
    }

}