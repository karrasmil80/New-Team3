package dev.newteam.newteam3.controller

import dev.newteam.newteam3.routes.RoutesManager
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.TextField
import org.lighthousegames.logging.logging
import org.mindrot.jbcrypt.BCrypt

private val logger = logging()
class LoginController {

    @FXML
    lateinit var passwordTextField: TextField

    @FXML
    lateinit var usernameTextField: TextField

    @FXML
    lateinit var cancelButton: Button

    @FXML
    lateinit var loginButton: Button

    fun initialize() {
        logger.debug { "Iniciando sesion..." }
        loginButton.setOnAction {
            val username = usernameTextField.text
            val password = passwordTextField.text

            if(login(username, password)){
                logger.debug { "Has iniciado sesion" }
                //Iniciamos la escena de la plantilla tras un login exitoso
                RoutesManager.initPlantillaStage()
                //Cerramos la anterior pantalla
                RoutesManager.escenaActiva.close()
            } else {
                Alert(Alert.AlertType.ERROR).apply {
                    title = "Error de autenticación"
                    headerText = "Vuelva a introducir sus datos correctamente"
                }.showAndWait()
            }

            cancelButton.setOnAction {
                usernameTextField.clear()
                passwordTextField.clear()
            }
        }
    }

    fun login(username: String, password: String): Boolean {
        val correctUsername = "admin"
        val hashedPassword = "\$2a\$12\$nb2drgFJPylkW9PfeQ01oeI0SFEH/XdBKMhJNpmLpACMqeTvVLUz2"

        // user:      admin
        // password:  contra123

        return username == correctUsername && BCrypt.checkpw(password, hashedPassword)
    }
}