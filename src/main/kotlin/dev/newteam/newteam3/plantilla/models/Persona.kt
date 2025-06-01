package dev.newteam.newteam3.plantilla.models

/**
 * Aquí se almacenan los datos de las Personas.
 */
abstract class Persona(
    val id: Int,
    var nombre: String,
    var apellido: String,
    var fechaNacimiento: String,
    var fechaIncorporacion: String,
    var salario: Double,
    var pais: String,
    val rol: String,
    val imagen: String,
    val equipo: Equipos
) {
    enum class Equipos {
        NEW_TEAM, MUPPET
    }
}