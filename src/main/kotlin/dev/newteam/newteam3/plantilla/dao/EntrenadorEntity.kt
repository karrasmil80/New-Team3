package dev.newteam.newteam3.plantilla.dao

import dev.newteam.newteam3.plantilla.models.Entrenador.Especializacion
import dev.newteam.newteam3.plantilla.models.Persona

class EntrenadorEntity(
    id: Int,
    nombre: String,
    apellido: String,
    fechaNacimiento: String,
    fechaIncorporacion: String,
    salario: Double,
    pais: String,
    equipo: Persona.Equipos,
    imagen: String,
    rol: String = "entrenador",
    val especializacion: Especializacion,
) : PersonaEntity(id, nombre, apellido, fechaNacimiento, fechaIncorporacion, salario, pais, rol, equipo, imagen)