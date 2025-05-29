package dev.newteam.newteam3.convocatoria.mapper

import dev.newteam.newteam3.convocatoria.dao.ConvocatoriaEntity
import dev.newteam.newteam3.convocatoria.models.Convocatoria

fun ConvocatoriaEntity.toModel() : Convocatoria {
    return Convocatoria(
        id = this.id,
        jornada = this.jornada,
        descripcion = this.descripcion,
        jugadoresConvocados = this.jugadoresConvocados,
    )
}

fun Convocatoria.toEntity() : ConvocatoriaEntity {
    return ConvocatoriaEntity(
        id = this.id,
        jornada = this.jornada,
        descripcion = this.descripcion,
        jugadoresConvocados = this.jugadoresConvocados,
    )
}