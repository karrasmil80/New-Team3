package plantilla.mapper

import dev.newteam.newteam3.plantilla.dao.EntrenadorEntity
import dev.newteam.newteam3.plantilla.dao.JugadorEntity
import dev.newteam.newteam3.plantilla.dto.PersonaDto
import dev.newteam.newteam3.plantilla.mapper.toEntity
import dev.newteam.newteam3.plantilla.mapper.toEntrenador
import dev.newteam.newteam3.plantilla.mapper.toJugador
import dev.newteam.newteam3.plantilla.mapper.toModel
import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona
import org.junit.jupiter.api.Test
import java.time.LocalDate
import org.junit.jupiter.api.Assertions.*


class PersonaMapperTest {

    @Test
    fun `test conversion PersonaDto a Model Jugador`() {
        val personaDto = PersonaDto(
            id = 1,
            nombre = "Cristiano",
            apellido = "Ronaldo",
            fecha_nacimiento = LocalDate.of(1985, 2, 5),
            fecha_incorporacion = LocalDate.of(2003, 8, 12),
            salario = 4000000.0,
            pais = "Portugal",
            imagen = "jugador",
            equipo = Persona.Equipos.MUPPET.toString(),
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.87,
            peso = 83.0,
            goles = 850,
            media_goles = 0.9,
            partidos_jugados = 1000,
            minutos_jugados = 90000,
            rol = "jugador",
            especializacion = null
        )

        val jugador = personaDto.toJugador()

        assertTrue(jugador is Jugador) // ✅ Validamos que la conversión es correcta

        // Validaciones de propiedades
        assertEquals(personaDto.id, jugador.id)
        assertEquals(personaDto.nombre, jugador.nombre)
        assertEquals(personaDto.apellido, jugador.apellido)
        assertEquals(personaDto.fecha_nacimiento, jugador.fechaNacimiento)
        assertEquals(personaDto.fecha_incorporacion, jugador.fechaIncorporacion)
        assertEquals(personaDto.salario, jugador.salario)
        assertEquals(personaDto.pais, jugador.pais)
        assertEquals(personaDto.posicion, jugador.posicion)
        assertEquals(personaDto.dorsal, jugador.dorsal)
        assertEquals(personaDto.altura, jugador.altura)
        assertEquals(personaDto.peso, jugador.peso)
        assertEquals(personaDto.goles, jugador.goles)
        assertEquals(personaDto.media_goles, jugador.mediaGoles)
        assertEquals(personaDto.partidos_jugados, jugador.partidosJugados)
        assertEquals(personaDto.minutos_jugados, jugador.minutosJugados)
        assertEquals(personaDto.imagen, jugador.imagen)
        assertEquals(personaDto.equipo, jugador.equipo.name)
        assertEquals(personaDto.rol, jugador.rol)
    }

    @Test
    fun `test conversion PersonaDto a Model Entrenador`() {
        val personaDto = PersonaDto(
            id = 2,
            nombre = "Carlo",
            apellido = "Ancelotti",
            fecha_nacimiento = LocalDate.of(1959, 6, 10),
            fecha_incorporacion = LocalDate.of(1995, 7, 1),
            salario = 7000000.0,
            pais = "Italia",
            imagen = "entrenador",
            equipo = Persona.Equipos.MUPPET.toString(),
            posicion = null,
            dorsal = null,
            altura = null,
            peso = null,
            goles = null,
            media_goles = null,
            partidos_jugados = null,
            minutos_jugados = null,
            rol = "entrenador",
            especializacion = Entrenador.Especializacion.ASISTENTE
        )

        val entrenador = personaDto.toEntrenador()



        assertTrue(entrenador is Entrenador) // ✅ Validamos que la conversión es correcta

        // Validaciones de propiedades
        assertEquals(personaDto.id, entrenador.id)
        assertEquals(personaDto.nombre, entrenador.nombre)
        assertEquals(personaDto.apellido, entrenador.apellido)
        assertEquals(personaDto.fecha_nacimiento, entrenador.fechaNacimiento)
        assertEquals(personaDto.fecha_incorporacion, entrenador.fechaIncorporacion)
        assertEquals(personaDto.salario, entrenador.salario)
        assertEquals(personaDto.pais, entrenador.pais)
        assertEquals(personaDto.imagen, entrenador.imagen)
        assertEquals(personaDto.equipo, entrenador.equipo.name)
        assertEquals(personaDto.especializacion, entrenador.especializacion)
        assertEquals(personaDto.rol, entrenador.rol)
    }


    @Test
    fun `test conversion PersonaDto a Entrenador`() {
        val personaDto = PersonaDto(
            id = 1,
            nombre = "Rafa",
            apellido = "Nadal",
            fecha_nacimiento = LocalDate.of(1970, 1, 1),
            fecha_incorporacion = LocalDate.of(1970, 1, 1),
            salario = 1000.0,
            pais = "España",
            imagen = "entrenador",
            equipo = Persona.Equipos.MUPPET.toString(),
            posicion = null,
            dorsal = null,
            altura = 1.80,  // ✅ Valor realista
            peso = null,
            goles = null,
            media_goles = null,
            partidos_jugados = null,
            minutos_jugados = null,
            rol = "entrenador", // ✅ Corregido a minúscula
            especializacion = Entrenador.Especializacion.PRINCIPAL
        )

        val persona = personaDto.toModel()

        assertTrue(persona is Entrenador) // ✅ Validamos que es un entrenador
        val entrenador = persona as Entrenador

        assertEquals(personaDto.id, entrenador.id)
        assertEquals(personaDto.nombre, entrenador.nombre)
        assertEquals(personaDto.apellido, entrenador.apellido)
        assertEquals(personaDto.fecha_nacimiento, entrenador.fechaNacimiento)
        assertEquals(personaDto.fecha_incorporacion, entrenador.fechaIncorporacion)
        assertEquals(personaDto.salario, entrenador.salario)
        assertEquals(personaDto.pais, entrenador.pais)
        assertEquals(personaDto.imagen, entrenador.imagen)
        assertEquals(personaDto.equipo, entrenador.equipo.name)
        assertEquals(personaDto.especializacion, entrenador.especializacion)
    }

    @Test
    fun `test conversion PersonaDto a Jugador`() {
        val personaDto = PersonaDto(
            id = 2,
            nombre = "Leo",
            apellido = "Messi",
            fecha_nacimiento = LocalDate.of(1987, 6, 24),
            fecha_incorporacion = LocalDate.of(2021, 8, 10),
            salario = 3000000.0,
            pais = "Argentina",
            imagen = "jugador",
            equipo = Persona.Equipos.NEW_TEAM.toString(),
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 10,
            altura = 1.70,
            peso = 72.0,
            goles = 800,
            media_goles = 0.85,
            partidos_jugados = 900,
            minutos_jugados = 81000,
            rol = "jugador", // ✅ Rol correcto
            especializacion = null
        )

        val persona = personaDto.toModel()

        assertTrue(persona is Jugador) // ✅ Validamos que es un jugador
        val jugador = persona as Jugador

        assertEquals(personaDto.id, jugador.id)
        assertEquals(personaDto.nombre, jugador.nombre)
        assertEquals(personaDto.apellido, jugador.apellido)
        assertEquals(personaDto.fecha_nacimiento, jugador.fechaNacimiento)
        assertEquals(personaDto.fecha_incorporacion, jugador.fechaIncorporacion)
        assertEquals(personaDto.salario, jugador.salario)
        assertEquals(personaDto.pais, jugador.pais)
        assertEquals(personaDto.posicion, jugador.posicion)
        assertEquals(personaDto.dorsal, jugador.dorsal)
        assertEquals(personaDto.altura, jugador.altura)
        assertEquals(personaDto.peso, jugador.peso)
        assertEquals(personaDto.goles, jugador.goles)
        assertEquals(personaDto.media_goles, jugador.mediaGoles)
        assertEquals(personaDto.partidos_jugados, jugador.partidosJugados)
        assertEquals(personaDto.minutos_jugados, jugador.minutosJugados)
        assertEquals(personaDto.imagen, jugador.imagen)
        assertEquals(personaDto.equipo, jugador.equipo.name)
    }

    @Test
    fun `test conversion PersonaEntity a Jugador`() {
        val jugadorEntity = JugadorEntity(
            id = 2,
            nombre = "Leo",
            apellido = "Messi",
            fechaNacimiento = LocalDate.of(1987, 6, 24),
            fechaIncorporacion = LocalDate.of(2021, 8, 10),
            salario = 3000000.0,
            pais = "Argentina",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 10,
            altura = 1.70,
            peso = 72.0,
            goles = 800,
            mediaGoles = 0.85,
            partidosJugados = 900,
            minutosJugados = 81000,
            imagen = "jugador",
            rol = "jugador",
            equipo = Persona.Equipos.NEW_TEAM
        )

        // Conversión de PersonaEntity a Jugador
        val jugador = jugadorEntity.toJugador()

        assertTrue(jugador is Jugador) // ✅ Validamos que la conversión es correcta

        // Validaciones de propiedades
        assertEquals(jugadorEntity.id, jugador.id)
        assertEquals(jugadorEntity.nombre, jugador.nombre)
        assertEquals(jugadorEntity.apellido, jugador.apellido)
        assertEquals(jugadorEntity.fechaNacimiento, jugador.fechaNacimiento)
        assertEquals(jugadorEntity.fechaIncorporacion, jugador.fechaIncorporacion)
        assertEquals(jugadorEntity.salario, jugador.salario)
        assertEquals(jugadorEntity.pais, jugador.pais)
        assertEquals(jugadorEntity.posicion, jugador.posicion)
        assertEquals(jugadorEntity.dorsal, jugador.dorsal)
        assertEquals(jugadorEntity.altura, jugador.altura)
        assertEquals(jugadorEntity.peso, jugador.peso)
        assertEquals(jugadorEntity.goles, jugador.goles)
        assertEquals(jugadorEntity.mediaGoles, jugador.mediaGoles)
        assertEquals(jugadorEntity.partidosJugados, jugador.partidosJugados)
        assertEquals(jugadorEntity.minutosJugados, jugador.minutosJugados)
        assertEquals(jugadorEntity.imagen, jugador.imagen)
        assertEquals(jugadorEntity.rol, jugador.rol)
        assertEquals(jugadorEntity.equipo, jugador.equipo)
    }

    @Test
    fun `test conversion PersonaEntity a Entrenador`() {
        val entrenadorEntity = EntrenadorEntity(
            id = 3,
            nombre = "Pep",
            apellido = "Guardiola",
            fechaNacimiento = LocalDate.of(1971, 1, 18),
            fechaIncorporacion = LocalDate.of(2008, 6, 1),
            salario = 5000000.0,
            pais = "España",
            equipo = Persona.Equipos.MUPPET,
            especializacion = Entrenador.Especializacion.PRINCIPAL,
            rol = "",
            imagen = "entrenador",
        )

        // Conversión de PersonaEntity a Entrenador
        val entrenador = entrenadorEntity.toEntrenador()

        assertTrue(entrenador is Entrenador) // ✅ Validamos que la conversión es correcta

        // Validaciones de propiedades
        assertEquals(entrenadorEntity.id, entrenador.id)
        assertEquals(entrenadorEntity.nombre, entrenador.nombre)
        assertEquals(entrenadorEntity.apellido, entrenador.apellido)
        assertEquals(entrenadorEntity.fechaNacimiento, entrenador.fechaNacimiento)
        assertEquals(entrenadorEntity.fechaIncorporacion, entrenador.fechaIncorporacion)
        assertEquals(entrenadorEntity.salario, entrenador.salario)
        assertEquals(entrenadorEntity.pais, entrenador.pais)
        assertEquals(entrenadorEntity.equipo, entrenador.equipo)
        assertEquals(entrenadorEntity.especializacion, entrenador.especializacion)
        assertEquals(entrenadorEntity.rol, entrenador.rol)

        // Validamos que el campo `imagen` se inicializa como una cadena vacía
        assertEquals("", entrenador.imagen) // ⚠️ Se asigna vacío en la conversión
    }

    @Test
    fun `test toJugador conversion`() {
        val jugador = Jugador(
            id = 10,
            nombre = "Lionel",
            apellido = "Messi",
            fechaNacimiento = LocalDate.of(1987,6,24),
            fechaIncorporacion = LocalDate.of(2004,10,16),
            salario = 75000000.0,
            pais = "Argentina",
            equipo = Persona.Equipos.MUPPET,
            imagen = "",
            rol = "Delantero",
            posicion = Jugador.Posicion.EXTREMO,
            dorsal = 10,
            altura = 1.70,
            peso = 72.0,
            goles = 800,
            mediaGoles = 0.89,
            partidosJugados = 900,
            minutosJugados = 70000
        )

        val convertido = jugador.toJugador()

        assertEquals(jugador.id, convertido.id)
        assertEquals(jugador.nombre, convertido.nombre)
        assertEquals(jugador.apellido, convertido.apellido)
        assertEquals(jugador.fechaNacimiento, convertido.fechaNacimiento)
        assertEquals(jugador.fechaIncorporacion, convertido.fechaIncorporacion)
        assertEquals(jugador.salario, convertido.salario)
        assertEquals(jugador.pais, convertido.pais)
        assertEquals(jugador.equipo, convertido.equipo)
        assertEquals("", convertido.imagen) // Imagen se setea a cadena vacía
        assertEquals(jugador.rol, convertido.rol)
        assertEquals(jugador.posicion, convertido.posicion)
        assertEquals(jugador.dorsal, convertido.dorsal)
        assertEquals(jugador.altura, convertido.altura)
        assertEquals(jugador.peso, convertido.peso)
        assertEquals(jugador.goles, convertido.goles)
        assertEquals(jugador.mediaGoles, convertido.mediaGoles)
        assertEquals(jugador.partidosJugados, convertido.partidosJugados)
        assertEquals(jugador.minutosJugados, convertido.minutosJugados)
        }

    @Test
    fun `test conversion Persona a Entrenador`() {
        val entrenador = Entrenador(
            id = 4,
            nombre = "Pep",
            apellido = "Guardiola",
            fechaNacimiento = LocalDate.of(1971, 1, 18),
            fechaIncorporacion = LocalDate.of(2008, 6, 1),
            salario = 5000000.0,
            pais = "España",
            equipo = Persona.Equipos.MUPPET,
            imagen = "",
            rol = "entrenador",
            especializacion = Entrenador.Especializacion.PORTEROS
        )

        // Conversión de Persona a Entrenador
        val entrenadorConvertido = entrenador.toEntrenador()

        assertTrue(entrenadorConvertido is Entrenador) // ✅ Validamos la conversión correcta

        // Validaciones de propiedades
        assertEquals(entrenador.id, entrenadorConvertido.id)
        assertEquals(entrenador.nombre, entrenadorConvertido.nombre)
        assertEquals(entrenador.apellido, entrenadorConvertido.apellido)
        assertEquals(entrenador.fechaNacimiento, entrenadorConvertido.fechaNacimiento)
        assertEquals(entrenador.fechaIncorporacion, entrenadorConvertido.fechaIncorporacion)
        assertEquals(entrenador.salario, entrenadorConvertido.salario)
        assertEquals(entrenador.pais, entrenadorConvertido.pais)
        assertEquals(entrenador.equipo, entrenadorConvertido.equipo)
        assertEquals(entrenador.especializacion, entrenadorConvertido.especializacion)
        assertEquals(entrenador.rol, entrenadorConvertido.rol)

        // Validamos que `imagen` se inicializa como una cadena vacía
        assertEquals("", entrenadorConvertido.imagen)
    }


    @Test
    fun `test toEntity conversion for Jugador`() {
        val jugador = Jugador(
            id = 10,
            nombre = "Lionel",
            apellido = "Messi",
            fechaNacimiento = LocalDate.of(1987,6,24),
            fechaIncorporacion = LocalDate.of(2004,10,16),
            salario = 75000000.0,
            pais = "Argentina",
            equipo = Persona.Equipos.MUPPET,
            imagen = "img.png",
            rol = "jugador",
            posicion = Jugador.Posicion.EXTREMO,
            dorsal = 10,
            altura = 1.70,
            peso = 72.0,
            goles = 800,
            mediaGoles = 0.89,
            partidosJugados = 900,
            minutosJugados = 70000
        )

        val convertido = jugador.toEntity() as JugadorEntity

        assertEquals(jugador.id, convertido.id)
        assertEquals(jugador.nombre, convertido.nombre)
        assertEquals(jugador.apellido, convertido.apellido)
        assertEquals(jugador.fechaNacimiento, convertido.fechaNacimiento)
        assertEquals(jugador.fechaIncorporacion, convertido.fechaIncorporacion)
        assertEquals(jugador.salario, convertido.salario)
        assertEquals(jugador.pais, convertido.pais)
        assertEquals(jugador.posicion, convertido.posicion)
        assertEquals(jugador.dorsal, convertido.dorsal)
        assertEquals(jugador.altura, convertido.altura)
        assertEquals(jugador.peso, convertido.peso)
        assertEquals(jugador.goles, convertido.goles)
        assertEquals(jugador.mediaGoles, convertido.mediaGoles)
        assertEquals(jugador.partidosJugados, convertido.partidosJugados)
        assertEquals(jugador.minutosJugados, convertido.minutosJugados)
        assertEquals(jugador.imagen, convertido.imagen)
        assertEquals(jugador.rol, convertido.rol)
        assertEquals(jugador.equipo, convertido.equipo)
    }

    @Test
    fun `test toEntity conversion for Entrenador`() {
        val entrenador = Entrenador(
            id = 1,
            nombre = "Pep",
            apellido = "Guardiola",
            fechaNacimiento = LocalDate.of(1971,1,18),
            fechaIncorporacion = LocalDate.of(2016,7,1),
            salario = 5000000.0,
            pais = "España",
            equipo = Persona.Equipos.NEW_TEAM,
            imagen = "img.png",
            rol = "entrenador",
            especializacion = Entrenador.Especializacion.ASISTENTE
        )

        val convertido = entrenador.toEntity() as EntrenadorEntity

        assertEquals(entrenador.id, convertido.id)
        assertEquals(entrenador.nombre, convertido.nombre)
        assertEquals(entrenador.apellido, convertido.apellido)
        assertEquals(entrenador.fechaNacimiento, convertido.fechaNacimiento)
        assertEquals(entrenador.fechaIncorporacion, convertido.fechaIncorporacion)
        assertEquals(entrenador.salario, convertido.salario)
        assertEquals(entrenador.pais, convertido.pais)
        assertEquals(entrenador.especializacion, convertido.especializacion)
        assertEquals(entrenador.imagen, convertido.imagen)
        assertEquals(entrenador.rol, convertido.rol)
        assertEquals(entrenador.equipo, convertido.equipo)
    }

}
