package dev.newteam.newteam3.user.validator

import com.github.michaelbull.result.Result

/**
 * Esta interfaz actúa a modo de contrato y obliga a todas las clases que hereden
 * de esta a implementar la función que ayuda a validar la
 * entrada de datos del Usuario.
 */
interface Validator<T, E> {
    fun validate (t: T): Result<T, E>
}