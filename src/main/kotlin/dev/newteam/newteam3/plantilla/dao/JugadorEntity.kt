package dev.newteam.newteam3.plantilla.dao

import dev.newteam.newteam3.plantilla.models.Equipo
import dev.newteam.newteam3.plantilla.models.Jugador.Posicion
import dev.newteam.newteam3.plantilla.models.Persona
import java.time.LocalDate

class JugadorEntity(
    id: Int,
    nombre: String,
    apellido: String,
    fechaNacimiento: LocalDate,
    fechaIncorporacion : LocalDate,
    salario : Double,
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
) : PersonaEntity(id, nombre, apellido, fechaNacimiento, fechaIncorporacion, salario, pais, rol, equipo, imagen)