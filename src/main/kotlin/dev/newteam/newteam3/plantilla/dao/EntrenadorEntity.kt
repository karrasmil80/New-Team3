package dev.newteam.newteam3.plantilla.dao

import dev.newteam.newteam3.plantilla.models.Entrenador.Especializacion
import java.time.LocalDate

class EntrenadorEntity(
    id: Int,
    nombre: String,
    apellido: String,
    fechaNacimiento: LocalDate,
    fechaIncorporacion : LocalDate,
    salario : Double,
    pais: String,
    imagen: String,
    rol: String = "entrenador",
    val especializacion: Especializacion,
    val equipo: String
) : PersonaEntity(id, nombre, apellido, fechaNacimiento, fechaIncorporacion, salario, pais, rol, imagen) {
}