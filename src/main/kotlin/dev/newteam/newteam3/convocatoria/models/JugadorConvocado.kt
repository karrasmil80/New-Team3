package dev.newteam.newteam3.convocatoria.models

import java.util.*

data class JugadorConvocado (
    val id: UUID = UUID.randomUUID(),
    val convocatoriaId: Int,
    val personaId: Int,
)