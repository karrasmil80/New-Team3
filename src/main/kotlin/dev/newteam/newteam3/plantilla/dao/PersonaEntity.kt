package dev.newteam.newteam3.plantilla.dao

import java.time.LocalDate

open class PersonaEntity(
    var id: Int,
    var nombre: String,
    var apellido: String,
    var fechaNacimiento: LocalDate,
    var fechaIncorporacion: LocalDate,
    var salario: Double,
    var pais: String,
    var rol: String,
    var imagen : String
) {

    fun getAppelido() = apellido

}