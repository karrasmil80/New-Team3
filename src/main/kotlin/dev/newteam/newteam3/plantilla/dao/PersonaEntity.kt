package dev.newteam.newteam3.plantilla.dao

import dev.newteam.newteam3.plantilla.models.Persona

open class PersonaEntity(
    var id: Int,
    var nombre: String,
    var apellido: String,
    var fechaNacimiento: String,
    var fechaIncorporacion: String,
    var salario: Double,
    var pais: String,
    var rol: String,
    var equipo: Persona.Equipos,
    var imagen: String
)