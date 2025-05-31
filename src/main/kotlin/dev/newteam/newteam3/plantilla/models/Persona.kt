package dev.newteam.newteam3.plantilla.models

/**
 * Aquí se almacenan los datos de las Personas.
 */
abstract class Persona(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val fechaNacimiento: String,
    val fechaIncorporacion: String,
    val salario: Double,
    val pais: String,
    val rol: String,
    val imagen: String,
    val equipo: Equipos
) {
    enum class Equipos {
        NEW_TEAM, MUPPET
    }
}