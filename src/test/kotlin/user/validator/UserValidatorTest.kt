package user.validator

import dev.newteam.newteam3.user.model.User
import dev.newteam.newteam3.user.validator.UserValidator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UserValidatorTest {
    private val validator = UserValidator()

    @Test
    @DisplayName("Test de Usuario Válido.")
    fun validateUserValido() {
        val user = User(
            id = 1,
            nombre = "admin",
            password = "contra123"
        )
        val result = validator.validate(user)

        assertTrue(result.isOk, "El validador debe devolver Ok")
        assertEquals(user, result.value, "El validador debe devolver el mismo user.")
    }

    @Test
    @DisplayName("Test de Usuario ID Inválido.")
    fun validateUserIdInvalido() {
        val user = User(
            id = 0,
            nombre = "admin",
            password = "contra123"
        )
        val result = validator.validate(user)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. ID negativo/nulo.", result.error.message)
    }

    @Test
    @DisplayName("Test de Usuario Nombre Inválido.")
    fun validateUserNombreInvalido() {
        val user = User(
            id = 1,
            nombre = "ajmin",
            password = "contra123"
        )
        val result = validator.validate(user)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. Nombre incorrecto.", result.error.message)

    }

    @Test
    @DisplayName("Test de Usuario Contraseña Inválida.")
    fun validateUserContraseñaInvalida() {
        val user = User(
            id = 1,
            nombre = "admin",
            password = "contra1234"
        )
        val result = validator.validate(user)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. Contraseña incorrecta.", result.error.message)

    }
}