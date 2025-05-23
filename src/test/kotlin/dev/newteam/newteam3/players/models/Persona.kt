package dev.newteam.newteam3.players.models

import java.time.LocalDate
import java.time.LocalDateTime

open class Persona(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val fechaNacimiento: LocalDate,
    val fechaIncorporacion: LocalDate,
    val salario: Double,
    val pais: String,
    val rol: String,
    val imagen : String
)