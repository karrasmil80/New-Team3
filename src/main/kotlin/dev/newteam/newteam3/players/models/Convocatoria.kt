package dev.newteam.newteam3.players.models

import java.time.LocalDateTime

data class Convocatoria(
    val id: Int,
    val jornada: LocalDateTime,
    val descripcion: String,
    val jugadores: List<Jugador>,
    val onceTitular: List<Jugador>,
    val entrenador: Entrenador
)
