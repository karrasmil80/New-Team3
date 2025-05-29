package dev.newteam.newteam3.convocatoria.models

import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador
import java.time.LocalDate
import java.time.LocalDateTime

data class Convocatoria (
    val id: Int,
    val jornada: LocalDate,
    val personalList: List<JugadorConvocado>,
)