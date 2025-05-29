package dev.newteam.newteam3.convocatoria.dao

import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface JugadorConvocadoDao {

    @SqlQuery("""
        SELECT id, persona_id AS personaId, convocatoria_id AS convocatoriaId
        FROM jugador_convocado
    """)
    fun findAll(): List<JugadorConvocadoEntity>

    @SqlQuery("""
        SELECT id, persona_id AS personaId, convocatoria_id AS convocatoriaId
        FROM jugador_convocado
        WHERE id = :id
    """)
    fun findById(@Bind("id") id: String): JugadorConvocadoEntity?

    @SqlQuery("""
        SELECT id, persona_id AS personaId, convocatoria_id AS convocatoriaId
        FROM jugador_convocado
        WHERE convocatoria_id = :convocatoriaId
    """)
    fun findByConvocatoriaId(@Bind("convocatoriaId") convocatoriaId: Int): List<JugadorConvocadoEntity>

    @SqlUpdate("""
        DELETE FROM jugador_convocado WHERE id = :id
    """)
    fun deleteById(@Bind("id") id: String): Int

    @SqlUpdate("""
        DELETE FROM jugador_convocado WHERE convocatoria_id = :convocatoriaId
    """)
    fun deleteByConvocatoriaId(@Bind("convocatoriaId") convocatoriaId: Int): Int

    @SqlUpdate("""
        INSERT INTO jugador_convocado (id, persona_id, convocatoria_id)
        VALUES (:id, :personaId, :convocatoriaId)
    """)
    fun save(@BindBean entity: JugadorConvocadoEntity): Int

    @SqlUpdate("DELETE FROM jugador_convocado")
    fun deleteAll(): Int
}