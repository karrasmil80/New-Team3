package dev.newteam.newteam3.convocatoria.error

/**
 * Clase sellada que almacena todos los tipos de
 * errores que se podría dar en el programa por parte de la convocatoria.
 */
sealed class ConvocatoriaError(val message: String) {

    /**
     * Almacena el error que se mostrará en el validador.
     *
     * @param message Mensaje de error.
     */
    class ConvocatoriaValidatorError(message: String): ConvocatoriaError("Datos no válidos. $message")

    /**
     * Almacena el error que se mostrará cuando no se encuentre el ID.
     *
     * @param id Identificador personal de la persona que no se ha podido encontrar.
     */
    class ConvocatoriaIdNotFound(id: Int): ConvocatoriaError("Persona no encontrada con id: $id")


    /**
     * Almacena el error que se mostrara en el servicio cuando haya algún error.
     *
     * @param message Mensaje de error.
     */

    class ConvocatoriaServiceException(message: String): ConvocatoriaError(message)
}