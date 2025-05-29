package dev.newteam.newteam3.convocatoria.dao

import java.time.LocalDate

data class ConvocatoriaEntity(
    val id : Int,
    val jornada : LocalDate
)