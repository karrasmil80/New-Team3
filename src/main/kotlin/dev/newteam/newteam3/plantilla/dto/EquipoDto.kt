package dev.newteam.newteam3.plantilla.dto

import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
class EquipoDto(
    val id : Int,
    val nombreEquipo : String,
    val fechaFundacion : String,
    val imagenEscudo : String,
    val ciudad : String,
    val pais : String,
)