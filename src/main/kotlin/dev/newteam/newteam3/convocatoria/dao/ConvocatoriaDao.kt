package dev.newteam.newteam3.convocatoria.dao

import dev.newteam.newteam3.plantilla.dao.PersonaEntity
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.kotlin.RegisterKotlinMapper
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import java.time.LocalDateTime


@RegisterKotlinMapper(ConvocatoriaEntity::class)
interface ConvocatoriaDao {

    @SqlQuery("SELECT id, jornada, descripcion FROM convocatoria")
    fun findAll(): List<ConvocatoriaEntity>

    @SqlQuery("SELECT id, jornada, descripcion FROM convocatoria WHERE id = :id")
    fun findById(@Bind("id")id: Int): ConvocatoriaEntity?

    @SqlUpdate("DELETE FROM convocatoria WHERE id = :id")
    fun delete(@Bind("id") id: Int) : Int

    @SqlUpdate("DELETE FROM convocatoria")
    fun deleteAll(): Int

    @SqlUpdate("""
    INSERT INTO convocatoria (jornada, descripcion)
    VALUES (:jornada, :descripcion)
""")
    @GetGeneratedKeys("id")
    fun save(
        @Bind("jornada") jornada: LocalDateTime,
        @Bind("descripcion") descripcion: String
    ): Int
}