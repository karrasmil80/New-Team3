package plantilla.mapper

import dev.newteam.newteam3.plantilla.dao.EquipoEntity
import dev.newteam.newteam3.plantilla.dto.EquipoDto
import dev.newteam.newteam3.plantilla.mapper.toDto
import dev.newteam.newteam3.plantilla.mapper.toEntity
import dev.newteam.newteam3.plantilla.mapper.toModel
import dev.newteam.newteam3.plantilla.models.Equipo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate

class EquipoMapperTest {
    @Test
    fun conversionEquipoDtoaEquipo() {
        val equipoDto = EquipoDto(
            id = 1,
            nombreEquipo = "FC Barcelona",
            fechaFundacion = LocalDate.of(1899, 11, 29),
            imagenEscudo = "barcelona.png",
            ciudad = "Barcelona",
            pais = "España"
        )

        val equipo = equipoDto.toModel()

        assertTrue(equipo is Equipo) // ✅ Validamos que la conversión es correcta

        // Validaciones de propiedades
        assertEquals(equipoDto.id, equipo.id)
        assertEquals(equipoDto.nombreEquipo, equipo.nombreEquipo)
        assertEquals(equipoDto.fechaFundacion, equipo.fechaFundacion)
        assertEquals(equipoDto.imagenEscudo, equipo.imagenEscudo)
        assertEquals(equipoDto.ciudad, equipo.ciudad)
        assertEquals(equipoDto.pais, equipo.pais)
    }

    @Test
    fun conversionEquipoDtoaEquipoDto() {
        val equipoDto = EquipoDto(
            id = 2,
            nombreEquipo = "Real Madrid",
            fechaFundacion = LocalDate.of(1902, 3, 6),
            imagenEscudo = "realmadrid.png",
            ciudad = "Madrid",
            pais = "España"
        )

        val equipoDtoConvertido = equipoDto.toDto()

        assertTrue(equipoDtoConvertido is EquipoDto) // ✅ Validamos que la conversión es correcta

        // Validaciones de propiedades
        assertEquals(equipoDto.id, equipoDtoConvertido.id)
        assertEquals(equipoDto.nombreEquipo, equipoDtoConvertido.nombreEquipo)
        assertEquals(equipoDto.fechaFundacion, equipoDtoConvertido.fechaFundacion)
        assertEquals(equipoDto.imagenEscudo, equipoDtoConvertido.imagenEscudo)
        assertEquals(equipoDto.ciudad, equipoDtoConvertido.ciudad)
        assertEquals(equipoDto.pais, equipoDtoConvertido.pais)
    }

    @Test
    fun conversionEquipoaEquipoEntity() {
        val equipo = Equipo(
            id = 3,
            nombreEquipo = "Manchester United",
            fechaFundacion = LocalDate.of(1878, 3, 5),
            imagenEscudo = "manchester.png",
            ciudad = "Manchester",
            pais = "Reino Unido"
        )

        val equipoEntity = equipo.toEntity()

        assertTrue(equipoEntity is EquipoEntity) // ✅ Validamos que la conversión es correcta

        // Validaciones de propiedades
        assertEquals(equipo.id, equipoEntity.id)
        assertEquals(equipo.nombreEquipo, equipoEntity.nombreEquipo)
        assertEquals(equipo.fechaFundacion, equipoEntity.fechaFundacion)
        assertEquals(equipo.imagenEscudo, equipoEntity.imagenEscudo)
        assertEquals(equipo.ciudad, equipoEntity.ciudad)
        assertEquals(equipo.pais, equipoEntity.pais)
    }




}