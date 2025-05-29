package dev.newteam.newteam3.plantilla.dao

import java.time.LocalDate

data class EquipoEntity(
    val id: Int,
    val nombreEquipo: String,
    val fechaFundacion: LocalDate,
    val imagenEscudo: String,
    val ciudad: String,
    val pais: String,
)