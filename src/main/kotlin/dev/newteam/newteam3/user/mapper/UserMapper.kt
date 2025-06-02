package dev.newteam.newteam3.user.mapper

import dev.newteam.newteam3.user.dao.UserEntity
import dev.newteam.newteam3.user.model.User

fun UserEntity.toModel(): User {
    return User(
        id = this.id,
        nombre = this.nombre,
        password = this.password,
    )
}
