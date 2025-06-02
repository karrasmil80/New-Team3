package dev.newteam.newteam3.user.dao

import dev.newteam.newteam3.user.model.User
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.kotlin.RegisterKotlinMapper
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

@RegisterKotlinMapper(UserEntity::class)
interface UserDao {

    @SqlQuery("SELECT * FROM usuario")
    fun findAll() : List<UserEntity>

    @SqlQuery("SELECT * FROM usuario WHERE id = :id")
    fun findById(@Bind("id")id: Int): UserEntity?

    @SqlUpdate("INSERT INTO usuario (id, nombre, password) VALUES (:id, :nombre, :password)")
    fun save(
        @Bind("id") id: Int,
        @Bind("nombre") nombre: String,
        @Bind("password") password: String
    ): UserEntity


    @SqlUpdate("DELETE FROM usuario WHERE id = :id")
    fun deleteById(@Bind("id")id: Int) : Int

    @SqlUpdate("DELETE FROM usuario")
    fun deleteAll() : Int

}