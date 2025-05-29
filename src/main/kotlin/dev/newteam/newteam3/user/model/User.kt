package dev.newteam.newteam3.user.model

/**
 * Aquí se almacenan los datos del usuario que interactuará con nuestro programa.
 */
data class User(
    val id : Int,
    val nombre : String,
    val password : String
)