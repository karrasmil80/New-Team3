package user.repositories

import dev.newteam.newteam3.user.dao.UserDao
import dev.newteam.newteam3.user.dao.UserEntity
import dev.newteam.newteam3.user.model.User
import dev.newteam.newteam3.user.repository.UserRepositoryImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class UserRepositoryImplMockitoTest {

    private lateinit var userDao : UserDao
    private lateinit var userRepository: UserRepositoryImpl
    @BeforeEach
    fun setup() {
        userDao = mock()
        userRepository = UserRepositoryImpl(userDao)
    }

    @Test
    fun findAll() {
        val userEntities = listOf(
            UserEntity(1, "Pablo", "pass1"),
            UserEntity(2, "Nico", "pass2")
        )

        whenever(userDao.findAll()).thenReturn(userEntities)

        val result = userRepository.findAll()

        verify(userDao).findAll()
        assertEquals(2, result.size)
        assertEquals("Pablo", result[0].nombre)
        assertEquals("Nico", result[1].nombre)
    }

    @Test
    fun `findById returns mapped user`() {
        val userEntity = UserEntity(1, "Pablo", "pass1")

        whenever(userDao.findById(1)).thenReturn(userEntity)

        val result = userRepository.findById(1)

        verify(userDao).findById(1)
        assertNotNull(result)
        assertEquals("Pablo", result?.nombre)
    }

    @Test
    fun save() {
        val user = User(1, "Pablo", "pass1")

        whenever(userDao.findById(1)).thenReturn(null)
        whenever(userDao.save(1, "Pablo", "pass1")).thenReturn(UserEntity(1, "Pablo", "pass1"))

        val savedUser = userRepository.save(user)

        verify(userDao).findById(1)
        verify(userDao).save(1, "Pablo", "pass1")
        assertEquals(user, savedUser)
    }

    @Test
    fun saveExiste() {
        val user = User(1, "Pablo", "pass1")
        val existingEntity = UserEntity(1, "Pablo", "pass1")

        whenever(userDao.findById(1)).thenReturn(existingEntity)

        val savedUser = userRepository.save(user)

        verify(userDao).findById(1)
        verify(userDao, never()).save(any(), any(), any())
        assertEquals(user, savedUser)
    }

    @Test
    fun deleteById() {
        whenever(userDao.deleteById(1)).thenReturn(1)

        val result = userRepository.deleteById(1)

        verify(userDao).deleteById(1)
        assertEquals(1, result)
    }

    @Test
    fun deleteAll() {
        whenever(userDao.deleteAll()).thenReturn(2)

        val result = userRepository.deleteAll()

        verify(userDao).deleteAll()
        assertEquals(2, result)
    }

    @Test
    fun saveAll() {
        val users = listOf(
            User(1, "Pablo", "pass1"),
            User(2, "Nico", "pass2")
        )

        whenever(userDao.findById(1)).thenReturn(null)
        whenever(userDao.save(1, "Pablo", "pass1")).thenReturn(UserEntity(1, "Pablo", "pass1"))
        whenever(userDao.findById(2)).thenReturn(null)
        whenever(userDao.save(2, "Nico", "pass2")).thenReturn(UserEntity(2, "Nico", "pass2"))

        val savedUsers = userRepository.saveAll(users)

        verify(userDao, times(2)).findById(any())
        verify(userDao).save(1, "Pablo", "pass1")
        verify(userDao).save(2, "Nico", "pass2")

        assertEquals(users.size, savedUsers.size)
        assertEquals(users[0], savedUsers[0])
        assertEquals(users[1], savedUsers[1])
    }
}