package dev.newteam.newteam3.plantilla.dao

import dev.newteam.newteam3.plantilla.models.Persona
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.kotlin.RegisterKotlinMapper
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import dev.newteam.newteam3.plantilla.dao.PersonaEntity


@RegisterKotlinMapper(PersonaEntity::class)

/**
 * Interfaz que definira las operaciones a realizar en la base de datos para posteriormente conectarlo por el repositorio
 */

interface PersonaDao {

    /**
     * Funcion que encuentra a todos los miembros de la plantilla en [tables.sql]
     */

    @SqlQuery("SELECT * FROM persona")
    fun findAll(): List<PersonaEntity>

    /**
     * Funcion que busca a un miembro de la plantilla por id en [tables.sql]
     */

    @SqlQuery("SELECT * FROM persona WHERE id = :id")
    fun findById(@Bind("id")id: Int): PersonaEntity?

    /**
     * Funcion que inserta un nuevo miembro en la plantilla de [data.sql]
     */

    @SqlUpdate(
        "INSERT INTO persona (id, nombre, apellido, fechaNacimiento, fechaIncorporacion, salario, pais, rol, equipo, imagen) " +
                "VALUES (:id, :nombre, :apellido, :fechaNacimiento, :fechaIncorporacion, :salario, :pais, :rol, :equipo, :imagen)"
    )
    @GetGeneratedKeys
    fun save(@BindBean personalEntity: PersonaEntity): Int

    /**
     * Funcion que borra el id de un miembro en [data.sql]
     */

    @SqlUpdate("DELETE FROM persona WHERE id = :id")
    fun delete(@Bind("id") id: Int) : Int

    /**
     * Funcion que elimina tods la informacion sobre un miembro en [data.sql]
     */

    @SqlUpdate("DELETE FROM persona")
    fun deleteAll() : Int

}