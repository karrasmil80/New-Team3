package dev.newteam.newteam3.plantilla.mapper

import dev.newteam.newteam3.plantilla.dao.EntrenadorEntity
import dev.newteam.newteam3.plantilla.dao.JugadorEntity
import dev.newteam.newteam3.plantilla.dao.PersonaEntity
import dev.newteam.newteam3.plantilla.dto.PersonaDto
import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona

    fun PersonaDto.toJugador(): Jugador {
        return Jugador(
            id = this.id,
            nombre = this.nombre,
            apellido = this.apellido,
            fechaNacimiento = this.fecha_nacimiento,
            fechaIncorporacion = this.fecha_incorporacion,
            salario = this.salario,
            pais = this.pais,
            posicion = this.posicion!!,
            rol = this.rol,
            dorsal = this.dorsal!!,
            altura = this.altura!!,
            peso = this.peso!!,
            goles = this.goles!!,
            mediaGoles = this.media_goles!!,
            partidosJugados = this.partidos_jugados!!,
            minutosJugados = this.minutos_jugados!!,
            imagen = this.imagen,
            equipo = Persona.Equipos.valueOf(equipo)
        )
    }

    fun PersonaDto.toEntrenador(): Entrenador {
        return Entrenador(
            id = this.id,
            nombre = this.nombre,
            apellido = this.apellido,
            fechaNacimiento = this.fecha_nacimiento,
            fechaIncorporacion = this.fecha_incorporacion,
            salario = this.salario,
            pais = this.pais,
            imagen = this.imagen,
            especializacion = this.especializacion!!,
            rol = this.rol,
            equipo = Persona.Equipos.valueOf(equipo)
        )
    }


    fun PersonaDto.toModel(): Persona {
        return if (this.rol == "jugador") {
            Jugador(
                id = this.id,
                nombre = this.nombre,
                apellido = this.apellido,
                fechaNacimiento = this.fecha_nacimiento,
                fechaIncorporacion = this.fecha_incorporacion,
                salario = this.salario,
                pais = this.pais,
                posicion = this.posicion!!,

                dorsal = this.dorsal!!,
                altura = this.altura!!,
                peso = this.peso!!,
                goles = this.goles!!,
                mediaGoles = this.media_goles!!,
                partidosJugados = this.partidos_jugados!!,
                minutosJugados = this.minutos_jugados!!,
                imagen = this.imagen,
                equipo = Persona.Equipos.valueOf(equipo)
            )
        } else {
            Entrenador(
                id = this.id,
                nombre = this.nombre,
                apellido = this.apellido,
                fechaNacimiento = this.fecha_nacimiento,
                fechaIncorporacion = this.fecha_incorporacion,
                salario = this.salario,
                pais = this.pais,
                especializacion = this.especializacion!!,
                imagen = this.imagen,
                equipo = Persona.Equipos.valueOf(equipo)
            )
        }
    }

    fun Persona.toEntity(): PersonaEntity {
        if (rol == "jugador") {
            val jugador = this as Jugador
            return JugadorEntity(
                id = this.id,
                nombre = jugador.nombre,
                apellido = jugador.apellido,
                fechaNacimiento = jugador.fechaNacimiento,
                fechaIncorporacion = jugador.fechaIncorporacion,
                salario = jugador.salario,
                pais = jugador.pais,
                posicion = jugador.posicion,
                dorsal = jugador.dorsal,
                altura = jugador.altura,
                peso = jugador.peso,
                goles = jugador.goles,
                mediaGoles = jugador.mediaGoles,
                partidosJugados = jugador.partidosJugados,
                minutosJugados = jugador.minutosJugados,
                imagen = jugador.imagen,
                rol = jugador.rol,
                equipo = jugador.equipo
            )
        } else {
            val entrenador = this as Entrenador
            return EntrenadorEntity(
                id = entrenador.id,
                nombre = entrenador.nombre,
                apellido = entrenador.apellido,
                fechaNacimiento = entrenador.fechaNacimiento,
                fechaIncorporacion = entrenador.fechaIncorporacion,
                salario = entrenador.salario,
                pais = entrenador.pais,
                especializacion = entrenador.especializacion,
                imagen = entrenador.imagen,
                rol = entrenador.rol,
                equipo = entrenador.equipo
            )
        }
    }

    fun PersonaEntity.toJugador(): Jugador {
        val jugador = this as JugadorEntity
        return Jugador(
            id = jugador.id,
            nombre = jugador.nombre,
            apellido = jugador.apellido,
            fechaNacimiento = jugador.fechaNacimiento,
            fechaIncorporacion = jugador.fechaIncorporacion,
            salario = jugador.salario,
            pais = jugador.pais,
            posicion = jugador.posicion,
            dorsal = jugador.dorsal,
            altura = jugador.altura,
            peso = jugador.peso,
            goles = jugador.goles,
            mediaGoles = jugador.mediaGoles,
            partidosJugados = jugador.partidosJugados,
            minutosJugados = jugador.minutosJugados,
            imagen = jugador.imagen,
            rol = jugador.rol,
            equipo = this.equipo,
        )
    }

    fun PersonaEntity.toEntrenador(): Entrenador {
        val entrenador = this as EntrenadorEntity
        return Entrenador(
            id = entrenador.id,
            nombre = entrenador.nombre,
            apellido = entrenador.apellido,
            fechaNacimiento = entrenador.fechaNacimiento,
            fechaIncorporacion = entrenador.fechaIncorporacion,
            salario = entrenador.salario,
            pais = entrenador.pais,
            equipo = entrenador.equipo,
            imagen = "",
            rol = this.rol,
            especializacion = this.especializacion
        )
    }


fun PersonaEntity.toModel(): Persona {
    return when (rol.trim().lowercase()) {
        "jugador" -> {
            val jugador = this as JugadorEntity
            Jugador(
                id = jugador.id,
                nombre = jugador.nombre,
                apellido = jugador.apellido,
                fechaNacimiento = jugador.fechaNacimiento,
                fechaIncorporacion = jugador.fechaIncorporacion,
                salario = jugador.salario,
                pais = jugador.pais,
                equipo = jugador.equipo,
                imagen = jugador.imagen,
                rol = jugador.rol,
                posicion = jugador.posicion,
                dorsal = jugador.dorsal,
                altura = jugador.altura,
                peso = jugador.peso,
                goles = jugador.goles,
                mediaGoles = jugador.mediaGoles,
                partidosJugados = jugador.partidosJugados,
                minutosJugados = jugador.minutosJugados
            )
        }

        "entrenador" -> {
            val entrenador = this as EntrenadorEntity
            Entrenador(
                id = entrenador.id,
                nombre = entrenador.nombre,
                apellido = entrenador.apellido,
                fechaNacimiento = entrenador.fechaNacimiento,
                fechaIncorporacion = entrenador.fechaIncorporacion,
                salario = entrenador.salario,
                pais = entrenador.pais,
                equipo = entrenador.equipo,
                imagen = entrenador.imagen,
                rol = entrenador.rol,
                especializacion = entrenador.especializacion,
            )
        }

        else -> throw IllegalStateException("Rol desconocido: '$rol'. No se puede mapear a un modelo.")
    }
}


fun Persona.toEntrenador(): Entrenador {
        val entrenador = this as Entrenador
        return Entrenador(
            id = entrenador.id,
            nombre = entrenador.nombre,
            apellido = entrenador.apellido,
            fechaNacimiento = entrenador.fechaNacimiento,
            fechaIncorporacion = entrenador.fechaIncorporacion,
            salario = entrenador.salario,
            pais = entrenador.pais,
            equipo = entrenador.equipo,
            imagen = "",
            rol = this.rol,
            especializacion = this.especializacion
        )
    }


    fun Persona.toJugador(): Jugador {
        val entrenador = this as Jugador
        return Jugador(
            id = entrenador.id,
            nombre = entrenador.nombre,
            apellido = entrenador.apellido,
            fechaNacimiento = entrenador.fechaNacimiento,
            fechaIncorporacion = entrenador.fechaIncorporacion,
            salario = entrenador.salario,
            pais = entrenador.pais,
            equipo = entrenador.equipo,
            imagen = "",
            rol = this.rol,
            posicion = this.posicion,
            dorsal = this.dorsal,
            altura = this.altura,
            peso = this.peso,
            goles = this.goles,
            mediaGoles = this.mediaGoles,
            partidosJugados = this.partidosJugados,
            minutosJugados = this.minutosJugados,
        )
    }



