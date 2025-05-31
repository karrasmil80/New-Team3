package dev.newteam.newteam3.plantilla.dao

data class EquipoEntity(
    val id: Int,
    val nombreEquipo: String,
    val fechaFundacion: String,
    val imagenEscudo: String,
    val ciudad: String,
    val pais: String,
)