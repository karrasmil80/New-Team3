package dev.newteam.newteam3.plantilla.models

/**
 * Aquí se almacenan los datos de las Personas tipo: Jugador.
 */
class Jugador(
    id: Int,
    nombre: String,
    apellido: String,
    fechaNacimiento: String,
    fechaIncorporacion: String,
    salario: Double,
    pais: String,
    imagen: String,
    rol: String = "jugador",
    equipo: Equipos,
    val posicion: Posicion,
    var dorsal: Int,
    var altura: Double,
    var peso: Double,
    var goles: Int,
    val mediaGoles: Double,
    var partidosJugados: Int,
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

    val nombreCompleto: String
        get() = "$nombre $apellido"

    enum class Posicion {
        PORTERO, DEFENSA, CENTROCAMPISTA, DELANTERO, EXTREMO, INTERIOR
    }

    override fun toString(): String {
        return ("Jugador(id=$id, nombre=$nombre, apellido=$apellido, fechaNacimiento=$fechaNacimiento, fechaIncorporacion=$fechaIncorporacion, salario=$salario, pais=$pais, posicion=$posicion, dorsal=$dorsal, altura=$altura, peso=$peso, goles=$goles,mediaGoles = $mediaGoles partidosJugados=$partidosJugados, minutosJugados=$minutosJugados)" )
    }
}

