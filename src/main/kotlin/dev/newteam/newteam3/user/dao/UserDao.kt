package dev.newteam.newteam3.user.dao

import dev.newteam.newteam3.user.model.User
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.kotlin.RegisterKotlinMapper
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

@RegisterKotlinMapper(UserEntity::class)
interface UserDao {

    @SqlQuery("SELECT * FROM user")
    fun findAll() : List<UserEntity>

    @SqlQuery("SELECT * FROM WHERE id = :id")
    fun findById(@Bind("id")id: Int): UserEntity?

    @SqlUpdate("INSERT INTO user (id, nombre, password) VALUES (:id, :nombre, :password)")
    @GetGeneratedKeys
    fun save(@Bind("id")user: UserEntity): Int

    @SqlUpdate("DELETE FROM user WHERE id = :id")
    fun deleteById(@Bind("id")id: Int) : Int

    @SqlUpdate("DELETE * FROM user")
    fun deleteAll() : Int

}