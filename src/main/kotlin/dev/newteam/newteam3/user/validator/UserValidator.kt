package dev.newteam.newteam3.user.validator

import com.github.michaelbull.result.Result
import dev.newteam.newteam3.plantilla.error.PersonaError
import dev.newteam.newteam3.plantilla.validator.Validator
import dev.newteam.newteam3.user.model.User

class UserValidator: Validator<User, PersonaError> {
    override fun validate(t: User): Result<User, PersonaError> {
        TODO("Not yet implemented")
    }
}