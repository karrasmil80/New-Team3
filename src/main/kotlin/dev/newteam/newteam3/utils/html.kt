package dev.newteam.newteam3.utils

import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona
import java.io.File
import java.time.LocalDate

class html {

    /**
     * Funciom que muestra el esqueleto del [HTML]
     */

    fun builderHtml(persona: List<Persona>) : String {

        val instanciaJugador = persona.filterIsInstance<Jugador>()
        val instanciaEntrenador = persona.filterIsInstance<Entrenador>()

        return """
     <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Document</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th {
            text-align: left;
            background-color: navy;
            color: white;
            padding: 10px;
        }
        td {
            padding: 10px;
            border: 1px solid #ddd;
        }
        h1 {
            text-align: center;
            color: navy;
        }
    </style>
</head>
<body>
    <h1>Informe del equipo MUPPET</h1>
    <table>
        <thead>
            <tr>
                <th colspan="4">Jugadores</th>
            </tr>
            <tr>
                <th>Nombre</th>
                <th>Posición</th>
                <th>Número</th>
                <th>Goles</th>
            </tr>
        </thead>
        <tbody>
                       ${instanciaJugador.joinToString("\n") {
            """
                        <tr>
                            <td>${it.nombre} ${it.apellido}</td>
                            <td>${it.posicion}</td>
                            <td>${it.dorsal}</td>
                            <td>${it.goles}</td>
                        </tr>
                        """.trimIndent()
        }}
        </tbody>
    </table>

    <table>
        <thead>
            <tr>
                <th colspan="4">Entrenadores</th>
            </tr>
            <tr>
                <th>Nombre</th>
                <th>Fecha de Incorporacion</th>
                <th>Especializacion</th>
                <th>Goles</th>
            </tr>
        </thead>
         <tbody>
                       ${instanciaEntrenador.joinToString("\n") {
            """
                        <tr>
                            <td>${it.nombre} ${it.apellido}</td>
                            <td>${it.fechaIncorporacion}</td>
                            <td>${it.especializacion}</td>
                        </tr>
                        """.trimIndent()
        }}
        </tbody>
    </table>
</body>
</html>

    """.trimIndent()
    }


    /**
     * Funcion que generará el reporte de [HTML]
     */

    fun generateReport(output : String, persona: List<Persona>) : String {
        val builder = builderHtml(persona)
        val file = File(output)
        file.writeText(builder)
        return file.absolutePath
    }


    fun builderHtmlMuppet(persona: List<Persona>) : String {

        val instanciaJugador = persona.filterIsInstance<Jugador>()

        return """
   <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Document</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th {
            text-align: left
            background-color: green;
            color: white;
            padding: 10px;
        }
        td {
            padding: 10px;
            border: 1px solid #ddd;
        }
        h1 {
            text-align: center;
            color: green;
        }
    </style>
</head>
<body>
    <h1>Informe de Jugadores - NEWTEAM</h1>
    <table>
        <thead>
            <tr>
                <th colspan="4">Jugadores</th>
            </tr>
            <tr>
                <th>Nombre</th>
                <th>Posición</th>
                <th>Número</th>
                <th>Goles</th>
            </tr>
        </thead>
        <tbody>
                       ${instanciaJugador.joinToString("\n") {
            """
                        <tr>
                            <td>${it.nombre} ${it.apellido}</td>
                            <td>${it.posicion}</td>
                            <td>${it.dorsal}</td>
                            <td>${it.goles}</td>
                        </tr>
                        """.trimIndent()
        }}
        </tbody>
    </table>

    <table>
        <thead>
            <tr>
                <th colspan="4">Entrenadores</th>
            </tr>
            <tr>
                <th>Nombre</th>
                <th>Rol</th>
                <th>Número</th>
                <th>Goles</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>José Martínez</td>
                <td>Jefe de Entrenadores</td>
                <td>N/A</td>
                <td>N/A</td>
            </tr>
            <tr>
                <td>Laura Fernández</td>
                <td>Asistente Técnico</td>
                <td>N/A</td>
                <td>N/A</td>
            </tr>
            <tr>
                <td>Raúl Sánchez</td>
                <td>Preparador Físico</td>
                <td>N/A</td>
                <td>N/A</td>
            </tr>
        </tbody>
    </table>
</body>
</html>
    """.trimIndent()
    }

    fun generateReportMuppet(output : String, persona: List<Persona>) : String {
        val builder = builderHtml(persona)
        val file = File(output)
        file.writeText(builder)
        return file.absolutePath
    }


}