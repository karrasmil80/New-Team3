package dev.newteam.newteam3.user.error

/**
 * Clase sellada que almacena todos los tipos de
 * errores que se podría dar en el programa en base de las Personas.
 */
sealed class UserError(val message: String) {

    /**
     * Almacena el error que se mostrará en el validador.
     *
     * @param message Mensaje de error.
     */
    class UserValidatorError(message: String): UserError("Datos no válidos. $message")

    /**
     * Almacena el error que se mostrará cuando no se encuentre el ID.
     *
     * @param id Identificador personal de la persona que no se ha podido encontrar.
     */
    class UserIdNotFound(id: Int): UserError("Persona no encontrada con id: $id")


    /**
     * Almacena el error que se mostrara en el servicio cuando haya algún error.
     *
     * @param message Mensaje de error.
     */
    class UserServiceException(message: String): UserError(message)
}