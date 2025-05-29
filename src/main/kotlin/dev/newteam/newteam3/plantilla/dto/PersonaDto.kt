package dev.newteam.newteam3.plantilla.dto

import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class PersonaDto(
    @SerialName("id")
    val id: Int,
    @SerialName("nombre")
    val nombre: String,
    @SerialName("apellido")
    val apellido: String,
    @SerialName("fecha_nacimiento")
    val fecha_nacimiento: LocalDate,
    @SerialName("fecha_incorporacion")
    val fecha_incorporacion: LocalDate,
    @SerialName("salario")
    val salario: Double,
    @SerialName("pais")
    val pais: String,
    @SerialName("posicion")
    val posicion: Jugador.Posicion?,
    @SerialName("especializacion")
    val especializacion: Entrenador.Especializacion?,
    @SerialName("dorsal")
    val dorsal: Int?,
    @SerialName("altura")
    val altura: Double?,
    @SerialName("peso")
    val peso: Double?,
    @SerialName("goles")
    val goles: Int?,
    @SerialName("media_goles")
    val media_goles: Double?,
    @SerialName("partidos_jugados")
    val partidos_jugados: Int?,
    @SerialName("minutos_jugados")
    val minutos_jugados: Int?,
    @SerialName("imagen")
    val imagen: String,
    @SerialName("rol")
    val rol: String,
    @SerialName("equipo")
    val equipo: String,
)
