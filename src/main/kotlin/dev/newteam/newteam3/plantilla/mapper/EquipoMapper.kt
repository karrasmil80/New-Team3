package dev.newteam.newteam3.plantilla.mapper

import dev.newteam.newteam3.plantilla.dao.EquipoEntity
import dev.newteam.newteam3.plantilla.dto.EquipoDto
import dev.newteam.newteam3.plantilla.models.Equipo

fun EquipoDto.toModel(): Equipo {
    return Equipo(
        id = this.id,
        nombreEquipo = this.nombreEquipo,
        fechaFundacion = this.fechaFundacion,
        imagenEscudo = this.imagenEscudo,
        ciudad = this.ciudad,
        pais = this.pais
    )
}

fun EquipoDto.toDto(): EquipoDto {
    return EquipoDto(
        id = this.id,
        nombreEquipo = this.nombreEquipo,
        fechaFundacion = this.fechaFundacion,
        imagenEscudo = this.imagenEscudo,
        ciudad = this.ciudad,
        pais = this.pais
    )
}

fun Equipo.toEntity(): EquipoEntity {
    return EquipoEntity(
        id = this.id,
        nombreEquipo = this.nombreEquipo,
        fechaFundacion = this.fechaFundacion,
        imagenEscudo = this.imagenEscudo,
        ciudad = this.ciudad,
        pais = this.pais
    )
}

