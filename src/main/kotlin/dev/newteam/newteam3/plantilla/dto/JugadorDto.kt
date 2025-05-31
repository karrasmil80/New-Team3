package dev.newteam.newteam3.plantilla.dto

import dev.newteam.newteam3.plantilla.models.Jugador
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
class JugadorDto(
    val id : Int,
    val nombre : String,
    val apellido : String,
    val fechaNacimiento : String,
    val fechaIncorporacion : String,
    val salario : Double,
    val pais : String,
    val posicion : Jugador.Posicion,
    val rol : String,
    val dorsal : Int,
    val altura : Double,
    val peso : Double,
    val goles : Int,
    val mediaGoles : Double,
    val partidosJugados : Int,
    val minutosJugados : Int,
    val imagen: String,
    val equipo : String,
)
