package dev.newteam.newteam3.convocatoria.dao

import dev.newteam.newteam3.plantilla.dao.PersonaDao
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.kotlin.RegisterKotlinMapper
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import java.time.LocalDate

@RegisterKotlinMapper(ConvocatoriaEntity::class)
interface ConvocatoriaDao {

    @SqlQuery("""
        SELECT id, jornada FROM convocatoria
    """)
    fun findAll(): List<ConvocatoriaEntity>

    @SqlQuery("""
        SELECT id AS id, jornada FROM convocatoria WHERE id = :id
    """)
    fun findById(@Bind("id") id: Int): ConvocatoriaEntity?

    @SqlQuery("""
        SELECT id , jornada FROM convocatoria WHERE jornada = :jornada
    """)
    fun findByJornada(@Bind("jornada") jornada: LocalDate): ConvocatoriaEntity?

    @SqlUpdate("""
        INSERT INTO convocatoria (jornada) VALUES (:jornada)
    """)
    @GetGeneratedKeys("id")
    fun save(@BindBean convocatoria: ConvocatoriaEntity): Int

    @SqlUpdate("DELETE FROM convocatoria WHERE id = :id")
    fun deleteById(@Bind("id") id: Int): Int

    @SqlUpdate("DELETE FROM convocatoria")
    fun deleteAll(): Int
}
