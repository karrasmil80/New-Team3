package user.dao

import dev.newteam.newteam3.database.JdbiManager
import dev.newteam.newteam3.user.dao.UserDao
import dev.newteam.newteam3.user.utils.provideUserDao
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.sqlobject.kotlin.onDemand
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

/*
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDaoTest {

    private lateinit var jdbi: Jdbi
    private lateinit var dao: UserDao

    @BeforeAll
    fun setup() {
        val jdbi = JdbiManager(
            databaseUrl = "jdbc:h2:mem:newteamDB;DB_CLOSE_DELAY=-1",
            databaseInitTables = true,
            databaseInitData = true,
            databaseLogger = false
        ).jdbi
        dao = provideUserDao(jdbi)
    }

    @BeforeEach
    fun reset() {
        dao.deleteAll()
    }

    @Test
    fun `test save and findById`() {
        dao.save(1, "juan", "1234")
        val user = dao.findById(1)
        assertNotNull(user)
        assertEquals("juan", user?.nombre)
    }

    @Test
    fun `test findAll`() {
        dao.save(1, "juan", "1234")
        dao.save(2, "ana", "abcd")
        val allUsers = dao.findAll()
        assertEquals(2, allUsers.size)
    }

    @Test
    fun `test deleteById`() {
        dao.save(1, "juan", "1234")
        val deletedRows = dao.deleteById(1)
        assertEquals(1, deletedRows)
        val user = dao.findById(1)
        assertNull(user)
    }

    @Test
    fun `test deleteAll`() {
        dao.save(1, "juan", "1234")
        dao.save(2, "ana", "abcd")
        val deleted = dao.deleteAll()
        assertEquals(2, deleted)
        assertEquals(0, dao.findAll().size)
    }
}
 */
