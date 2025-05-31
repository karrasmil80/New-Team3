package dev.newteam.newteam3.plantilla.dto

import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonaDto(
    @SerialName("id")
    val id: Int,
    @SerialName("nombre")
    val nombre: String,
    @SerialName("apellidos")
    val apellidos: String,
    @SerialName("fecha_nacimiento")
    val fecha_nacimiento: String,
    @SerialName("fecha_incorporacion")
    val fecha_incorporacion: String,
    @SerialName("salario")
    val salario: Double,
    @SerialName("pais")
    val pais: String,
    @SerialName("posicion")
    val posicion: Jugador.Posicion?,
    @SerialName("especialidad")
    val especialidad: Entrenador.Especializacion? = null,
    @SerialName("dorsal")
    val dorsal: Int?,
    @SerialName("altura")
    val altura: Double?,
    @SerialName("peso")
    val peso: Double?,
    @SerialName("goles")
    val goles: Int?,
    @SerialName("media_goles")
    val media_goles: Double? = null,   // no está en JSON, puede ser opcional o con valor por defecto
    @SerialName("partidos_jugados")
    val partidos_jugados: Int?,
    @SerialName("minutos_jugados")
    val minutos_jugados: Int?,
    @SerialName("imagen")
    val imagen: String? = null,         // no está en JSON, hazlo opcional si no siempre se recibe
    @SerialName("rol")
    val rol: String,
    @SerialName("equipo")
    val equipo: String,
)
