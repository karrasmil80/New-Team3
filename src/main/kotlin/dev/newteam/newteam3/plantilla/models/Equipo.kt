package dev.newteam.newteam3.plantilla.models

import java.time.LocalDate

class Equipo(
    val id: Int,
    val nombreEquipo: String,
    val fechaFundacion: LocalDate,
    val imagenEscudo: String,
    val ciudad: String,
    val pais: String,
) {

    override fun toString(): String {
        return "(Equipo(id= $id, nombreEquipo = $nombreEquipo, fechaFundacion = $fechaFundacion, ciudad = $ciudad, pais = $pais)"
    }
}