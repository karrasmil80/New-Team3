package dev.newteam.newteam3.user.service

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Err
import dev.newteam.newteam3.user.error.UserError
import dev.newteam.newteam3.user.model.User
import dev.newteam.newteam3.user.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*

@ExtendWith(MockitoExtension::class)
class UserServiceImplTest {

    @Mock
    lateinit var repository: UserRepository

    @InjectMocks
    lateinit var service: UserServiceImpl

    private val user = User(1, "Pablo", "1234")
    private val user2 = User(2, "Nico", "abcd")

    @Nested
    @DisplayName("Casos correctos")
    inner class CasosCorrectos {

        @Test
        @DisplayName("Debe devolver todos los usuarios")
        fun findAll() {
            whenever(repository.findAll()).thenReturn(listOf(user, user2))

            val result = service.findAll()

            assertTrue(result.isOk)
            verify(repository, times(1)).findAll()
        }

        @Test
        @DisplayName("Debe devolver usuario por ID")
        fun findByIdFound() {
            whenever(repository.findById(1)).thenReturn(user)

            val result = service.findById(1)

            assertTrue(result.isOk)
            verify(repository).findById(1)
        }

        @Test
        @DisplayName("Debe devolver error si el usuario no existe")
        fun findByIdNotFound() {
            whenever(repository.findById(99)).thenReturn(null)

            val result = service.findById(99)

            assertTrue(result.isErr)
            verify(repository).findById(99)
        }

        @Test
        @DisplayName("Debe guardar usuario")
        fun save() {
            whenever(repository.save(user)).thenReturn(user)

            val result = service.save(user)

            assertTrue(result.isOk)
            verify(repository).save(user)
        }

        @Test
        @DisplayName("Debe devolver error si falla al guardar")
        fun saveError() {
            whenever(repository.save(user)).thenAnswer {
                throw RuntimeException("Fallo al guardar")
            }

            val result = service.save(user)

            assertTrue(result.isErr)
            verify(repository).save(user)
        }

        @Test
        @DisplayName("Debe eliminar usuario por ID")
        fun deleteById() {
            whenever(repository.deleteById(1)).thenReturn(1)

            val result = service.deleteById(1)

            assertTrue(result.isOk)
            verify(repository).deleteById(1)
        }

        @Test
        @DisplayName("Debe devolver error al eliminar por ID si hay fallo")
        fun deleteByIdError() {
            whenever(repository.deleteById(1)).thenAnswer {
                throw RuntimeException("Error al eliminar")
            }

            val result = service.deleteById(1)

            assertTrue(result.isErr)
            verify(repository).deleteById(1)
        }

        @Test
        @DisplayName("Debe eliminar todos los usuarios")
        fun deleteAll() {
            whenever(repository.deleteAll()).thenReturn(5)

            val result = service.deleteAll()

            assertTrue(result.isOk)
            verify(repository).deleteAll()
        }

        @Test
        @DisplayName("Debe devolver error si falla deleteAll")
        fun deleteAllError() {
            whenever(repository.deleteAll()).thenAnswer {
                throw RuntimeException("Fallo en deleteAll")
            }

            val result = service.deleteAll()

            assertTrue(result.isErr)
            verify(repository).deleteAll()
        }

        @Test
        @DisplayName("Debe guardar todos los usuarios")
        fun saveAll() {
            val list = listOf(user, user2)
            whenever(repository.saveAll(list)).thenReturn(list)

            val result = service.saveAll(list)

            assertTrue(result.isOk)
            verify(repository).saveAll(list)
        }

        @Test
        @DisplayName("Debe devolver error si falla saveAll")
        fun saveAllError() {
            val list = listOf(user, user2)
            whenever(repository.saveAll(list)).thenAnswer {
                throw RuntimeException("Fallo en saveAll")
            }

            val result = service.saveAll(list)

            assertTrue(result.isErr)
            verify(repository).saveAll(list)
        }
    }
}
