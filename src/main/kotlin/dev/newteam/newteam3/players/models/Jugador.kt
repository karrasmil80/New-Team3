package dev.newteam.newteam3.players.models

import java.time.LocalDate


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
    val posicion: Posicion,
    val dorsal: Int,
    val altura: Double,
    val peso: Double,
    val goles: Int,
    val mediaGoles: Double,
    val partidosJugados: Int,
    val minutosJugados: Int,
    val equipo: String
): Persona(id,
    nombre,
    apellido,
    fechaNacimiento,
    fechaIncorporacion,
    salario,
    pais,
    imagen,
    rol
) {

    enum class Posicion {
        PORTERO, DEFENSA, MEDIOCENTRO, DELANTERO
    }

    override fun toString(): String {
        return ("Jugador(id=$id, nombre=$nombre, apellido=$apellido, fechaNacimiento=$fechaNacimiento, fechaIncorporacion=$fechaIncorporacion, salario=$salario, pais=$pais, posicion=$posicion, dorsal=$dorsal, altura=$altura, peso=$peso, goles=$goles,mediaGoles = $mediaGoles partidosJugados=$partidosJugados, minutosJugados=$minutosJugados)" )
    }
}