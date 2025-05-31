package dev.newteam.newteam3.plantilla.extensions

import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador


/**
 * Funcion de extension que crea una copia de los datos de un [Entrenador]
 */

fun Entrenador.copy(id: Int) : Entrenador {
    return Entrenador(
        id = this.id,
        nombre = this.nombre,
        apellido = this.apellido,
        fechaNacimiento = this.fechaNacimiento.toString(),
        fechaIncorporacion = this.fechaIncorporacion.toString(),
        salario = this.salario,
        pais = this.pais,
        imagen = this.imagen,
        rol = this.rol,
        equipo = this.equipo,
        especializacion = this.especializacion,
    )
}

/**
 * Funcion de extension que crea una copia de los datos de un [Jugador]
 */

fun Jugador.copy(id: Int): Jugador {
    return Jugador(
        id = this.id,
        nombre = this.nombre,
        apellido = this.apellido,
        fechaNacimiento = this.fechaNacimiento.toString(),
        fechaIncorporacion = this.fechaIncorporacion.toString(),
        salario = this.salario,
        pais = this.pais,
        imagen = this.imagen,
        rol = this.rol,
        equipo = this.equipo,
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
