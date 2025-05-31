package dev.newteam.newteam3.plantilla.dto

import dev.newteam.newteam3.plantilla.models.Entrenador
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
class EntrenadorDto(
    val id : Int,
    val nombre : String,
    val apellido : String,
    val fechaNacimiento : String,
    val fechaIncorporacion : String,
    val salario : Double,
    val pais : String,
    val imagen: String,
    val especializacion: Entrenador.Especializacion,
    val rol : String,
    val equipo : String
)