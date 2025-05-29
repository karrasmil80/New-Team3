package dev.newteam.newteam3.convocatoria.dao

import dev.newteam.newteam3.convocatoria.models.JugadorConvocatoria
import java.time.LocalDateTime

data class ConvocatoriaEntity(
    val id: Int,
    val jornada: LocalDateTime,
    val descripcion: String,
    val jugadoresConvocados: List<JugadorConvocatoria>,
)
