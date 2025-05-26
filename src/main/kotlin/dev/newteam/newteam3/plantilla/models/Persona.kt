package dev.newteam.newteam3.plantilla.models

import java.time.LocalDate

abstract class Persona(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val fechaNacimiento: LocalDate,
    val fechaIncorporacion: LocalDate,
    val salario: Double,
    val pais: String,
    val rol: String,
    val imagen : String,
    val equipo: Equipos
) {
    enum class Equipos {
        NEW_TEAM, MUPPET
    }
}