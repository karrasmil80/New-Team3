package dev.newteam.newteam3.plantilla.error

/**
 * Clase sellada que almacena todos los tipos de
 * errores que se podría dar en el programa.
 */
sealed class PersonaError(val message: String) {

    /**
     * Almacena el error que se mostrará en el validador.
     *
     * @param message Mensaje de error.
     */
    class PersonaValidatorError(message: String) : PersonaError("Persona no válida: $message")

    /**
     * Almacena el error que se mostrará cuando no se encuentre el ID.
     *
     * @param id Identificador personal de la persona que no se ha podido encontrar.
     */
    class PersonaIdNotFound(id: Long) : PersonaError("Persona no encontrada con id: $id")

    /**
     * Almacena el error que se mostrará cuando el storage del programa no encuentre el archivo
     *
     * @param message Mensaje de error.
     */
    class PersonaStorageError(message: String) : PersonaError(message)

    /**
     *  Almacena el error que se mostrará cuando la base de datos de la persona no se haya conectado.
     *
     *  @param message Mensaje de error.
     */
    class PersonaDatabaseException(message: String): PersonaError(message)
}