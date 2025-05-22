package dev.newteam.newteam3.players.models

class User(
    val nombreDeUser: String,
    val passwordHash: String,
    val rol: Rol
) {
    enum class Rol {
        ADMIN, USER
    }

}