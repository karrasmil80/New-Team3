package dev.newteam.newteam3.plantilla.mapper

import dev.newteam.newteam3.plantilla.dao.JugadorEntity
import dev.newteam.newteam3.plantilla.dto.JugadorDto
import dev.newteam.newteam3.plantilla.dto.PersonaDto
import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona


fun Jugador.toDto() : PersonaDto {
    return PersonaDto(
        id = id,
        nombre = nombre,
        apellido = apellido,
        fecha_nacimiento = fechaNacimiento,
        fecha_incorporacion = fechaIncorporacion,
        salario = salario,
        pais = pais,
        posicion = posicion,
        dorsal = dorsal,
        altura = altura,
        peso = peso,
        goles = goles,
        media_goles = mediaGoles,
        partidos_jugados = partidosJugados,
        minutos_jugados = minutosJugados,
        imagen = imagen,
        rol = rol,
        equipo = equipo,
        especializacion = null
    )
}

fun JugadorDto.toModel() : Persona {
    return Jugador(
        id = this.id,
        nombre = this.nombre,
        apellido = this.apellido,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        posicion = posicion,
        dorsal = this.dorsal,
        altura = this.altura,
        peso = this.peso,
        goles = this.goles,
        mediaGoles = this.mediaGoles,
        partidosJugados = this.partidosJugados,
        minutosJugados = this.minutosJugados,
        imagen = this.imagen,
        rol = this.rol,
        equipo = this.equipo,
    )
}

fun Jugador.toEntity(): JugadorEntity {
    return JugadorEntity(
        id = this.id,
        nombre = this.nombre,
        apellido = this.apellido,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        posicion = posicion,
        dorsal = this.dorsal,
        altura = this.altura,
        peso = this.peso,
        goles = this.goles,
        mediaGoles = this.mediaGoles,
        partidosJugados = this.partidosJugados,
        minutosJugados = this.minutosJugados,
        imagen = this.imagen,
        rol = this.rol,
        equipo = this.equipo
    )
}

fun JugadorEntity.toModel(): Jugador {
    return Jugador(
        id = this.id,
        nombre = this.nombre,
        apellido = this.apellido,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        posicion = posicion,
        dorsal = this.dorsal,
        altura = this.altura,
        peso = this.peso,
        goles = this.goles,
        mediaGoles = this.mediaGoles,
        partidosJugados = this.partidosJugados,
        minutosJugados = this.minutosJugados,
        imagen = this.imagen,
        rol = this.rol,
        equipo = this.equipo

    )
}

fun JugadorDto.toEntity(): JugadorEntity {
    return JugadorEntity(
        id = this.id,
        nombre = this.nombre,
        apellido = this.apellido,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        posicion = posicion,
        dorsal = this.dorsal,
        altura = this.altura,
        peso = this.peso,
        goles = this.goles,
        mediaGoles = this.mediaGoles,
        partidosJugados = this.partidosJugados,
        minutosJugados = this.minutosJugados,
        imagen = this.imagen,
        rol = this.rol,
        equipo = this.equipo
    )
}

/*
fun PersonaViewModel.JugadorState.toModel(): Jugador {
    return Jugador(
        id = this.id,
        nombre = this.nombre,
        apellido = this.apellido,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        posicion = posicion,
        dorsal = this.dorsal,
        altura = this.altura,
        peso = this.peso,
        goles = this.goles,
        mediaGoles = this.mediaGoles,
        partidosJugados = this.partidosJugados,
        minutosJugados = this.minutosJugados,
        imagen = this.imagen,
        rol = this.rol,
        equipo = this.equipo
    )
}

 */