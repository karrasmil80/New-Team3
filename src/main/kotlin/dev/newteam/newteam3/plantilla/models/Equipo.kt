package dev.newteam.newteam3.plantilla.models

/**
 * Aquí se almacenan los datos del Equipo.
 */
class Equipo(
    val id: Int,
    val nombreEquipo: String,
    val fechaFundacion: String,
    val imagenEscudo: String,
    val ciudad: String,
    val pais: String,
) {

    override fun toString(): String {
        return "(Equipo(id= $id, nombreEquipo = $nombreEquipo, fechaFundacion = $fechaFundacion, ciudad = $ciudad, pais = $pais)"
    }
}