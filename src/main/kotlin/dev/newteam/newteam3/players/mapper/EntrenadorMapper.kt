package dev.newteam.newteam3.players.mapper

import dev.newteam.newteam3.players.dto.EntrenadorDto
import dev.newteam.newteam3.players.dto.PersonaDto
import dev.newteam.newteam3.players.models.Entrenador
import dev.newteam.newteam3.players.models.Persona

fun Entrenador.toDto() : PersonaDto {
    return PersonaDto(
        id = id,
        nombre = nombre,
        apellido = apellido,
        fecha_nacimiento = fechaNacimiento,
        fecha_incorporacion = fechaIncorporacion,
        salario = salario,
        pais = pais,
        posicion = null,
        altura = null,
        dorsal = null,
        peso = null,
        goles = null,
        media_goles = null,
        partidos_jugados = null,
        minutos_jugados = null,
        imagen = imagen,
        rol = rol,
        especializacion = especializacion,
        equipo = equipo
    )
}

fun EntrenadorDto.toModel() : Entrenador {
    return Entrenador(
        id = this.id,
        nombre = this.nombre,
        apellido = this.apellido,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        imagen = this.imagen,
        especializacion = this.especializacion,
        rol = this.rol,
        equipo = this.equipo
    )
}


fun Entrenador.toEntity() : EntrenadorEntity {
    return EntrenadorEntity(
        id = this.id,
        nombre = this.nombre,
        apellido = this.apellido,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        imagen = this.imagen,
        especializacion = this.especializacion,
        rol = this.rol,
        equipo = this.equipo
    )
}

fun EntrenadorEntity.toModel(): Entrenador {
    return Entrenador(
        id = this.id,
        nombre = this.nombre,
        apellido = this.apellido,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        imagen = this.imagen,
        especializacion = this.especializacion,
        rol = this.rol,
        equipo = this.equipo
    )
}

fun EntrenadorDto.toEntity() : EntrenadorEntity {
    return EntrenadorEntity(
        id = this.id,
        nombre = this.nombre,
        apellido = this.apellido,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        imagen = this.imagen,
        especializacion = this.especializacion,
        rol = this.rol,
        equipo = this.equipo
    )
}