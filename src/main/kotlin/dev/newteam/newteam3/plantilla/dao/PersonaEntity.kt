package dev.newteam.newteam3.plantilla.dao

import dev.newteam.newteam3.plantilla.models.Equipo
import dev.newteam.newteam3.plantilla.models.Persona
import java.time.LocalDate

open class PersonaEntity(
    var id: Int,
    var nombre: String,
    var apellido: String,
    var fechaNacimiento: LocalDate,
    var fechaIncorporacion: LocalDate,
    var salario: Double,
    var pais: String,
    var rol: String,
    var equipo: Persona.Equipos,
    var imagen : String
)