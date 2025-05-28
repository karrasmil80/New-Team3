package dev.newteam.newteam3.plantilla.models

import java.time.LocalDate

/**
 * Aquí se almacenan los datos de las Personas tipo: Jugador.
 */
class Jugador(
    id: Int,
    nombre: String,
    apellido: String,
    fechaNacimiento: LocalDate,
    fechaIncorporacion : LocalDate,
    salario : Double,
    pais: String,
    imagen: String,
    rol: String = "jugador",
    equipo: Equipos,
    val posicion: Posicion,
    val dorsal: Int,
    val altura: Double,
    val peso: Double,
    val goles: Int,
    val mediaGoles: Double,
    val partidosJugados: Int,
    val minutosJugados: Int,
): Persona(id,
    nombre,
    apellido,
    fechaNacimiento,
    fechaIncorporacion,
    salario,
    pais,
    imagen,
    rol,
    equipo
) {

    enum class Posicion {
        PORTERO, DEFENSA, MEDIOCENTRO, DELANTERO, EXTREMO, INTERIOR
    }

    override fun toString(): String {
        return ("Jugador(id=$id, nombre=$nombre, apellido=$apellido, fechaNacimiento=$fechaNacimiento, fechaIncorporacion=$fechaIncorporacion, salario=$salario, pais=$pais, posicion=$posicion, dorsal=$dorsal, altura=$altura, peso=$peso, goles=$goles,mediaGoles = $mediaGoles partidosJugados=$partidosJugados, minutosJugados=$minutosJugados)" )
    }
}