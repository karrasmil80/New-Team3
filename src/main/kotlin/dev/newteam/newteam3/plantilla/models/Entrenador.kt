package dev.newteam.newteam3.plantilla.models

import java.time.LocalDate

class Entrenador(
    id: Int,
    nombre: String,
    apellido: String,
    fechaNacimiento: LocalDate,
    fechaIncorporacion : LocalDate,
    salario : Double,
    pais: String,
    imagen: String,
    rol: String = "entrenador",
    equipo: Equipos,
    val especializacion: Especializacion,
): Persona(id,
    nombre,
    apellido,
    fechaNacimiento,
    fechaIncorporacion,
    salario,
    pais,
    imagen,
    rol,
    equipo,
) {
    enum class Especializacion {
        PRINCIPAL, ASISTENTE, PORTEROS
    }

    override fun toString(): String {
        return ("(Entrenador(id=$id, nombre=$nombre, apellido=$apellido, fechaNacimiento=$fechaNacimiento, fechaIncorporacion= $fechaIncorporacion, salario= $salario, pais=$pais, especializacio= $especializacion, equipo=$equipo)")
    }
}