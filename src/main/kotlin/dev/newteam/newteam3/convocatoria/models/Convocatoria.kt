package dev.newteam.newteam3.convocatoria.models

import dev.newteam.newteam3.plantilla.models.Entrenador
import java.time.LocalDateTime

data class Convocatoria(
    val id: Int,
    val jornada: LocalDateTime,
    val descripcion: String,
    val jugadoresConvocados: List<JugadorConvocatoria>,
    val onceTitular: List<Int>, //id de los jugadores titulares
)
