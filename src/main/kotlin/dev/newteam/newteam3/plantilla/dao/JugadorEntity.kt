package dev.newteam.newteam3.plantilla.dao

import dev.newteam.newteam3.plantilla.models.Jugador.Posicion
import dev.newteam.newteam3.plantilla.models.Persona

class JugadorEntity(
    id: Int,
    nombre: String,
    apellido: String,
    fechaNacimiento: String,
    fechaIncorporacion: String,
    salario: Double,
    pais: String,
    imagen: String,
    equipo: Persona.Equipos,
    rol: String = "jugador",
    val posicion: Posicion,
    val dorsal: Int,
    val altura: Double,
    val peso: Double,
    val goles: Int,
    val mediaGoles: Double,
    val partidosJugados: Int,
    val minutosJugados: Int,
) : PersonaEntity(id, nombre, apellido,
    fechaNacimiento.toString(), fechaIncorporacion.toString(), salario, pais, rol, equipo, imagen)