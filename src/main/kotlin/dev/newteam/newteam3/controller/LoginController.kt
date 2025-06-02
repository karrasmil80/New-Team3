package dev.newteam.newteam3.controller

import dev.newteam.newteam3.routes.RoutesManager
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.scene.control.*
import org.lighthousegames.logging.logging
import org.mindrot.jbcrypt.BCrypt

private val logger = logging()
class LoginController {


    @FXML
    lateinit var closeButton: Button

    @FXML
    lateinit var passw: PasswordField

    @FXML
    lateinit var usernameTextField: TextField

    @FXML
    lateinit var cancelButton: Button

    @FXML
    lateinit var loginButton: Button

    @FXML
    lateinit var userAccessButton: Button

    fun initialize() {
        logger.debug { "Iniciando sesion..." }

        loginButton.setOnAction {
            val username = usernameTextField.text
            val password = passw.text

            if(login(username, password)){
                logger.debug { "Has iniciado sesion" }
                RoutesManager.initPlantillaStage()

                loginButton.scene.window.hide()
            } else {
                Alert(Alert.AlertType.ERROR).apply {
                    title = "Error de autenticación"
                    headerText = "Vuelva a introducir sus datos correctamente"
                }.showAndWait()
            }

            onAppExit()
        }

        cancelButton.setOnAction {
            usernameTextField.clear()
            passw.clear()
        }

        userAccessButton.setOnAction {
            logger.debug { "Accediendo como usuario libre..." }
            RoutesManager.initUserStage()

            userAccessButton.scene.window.hide()
        }
    }


    fun login(username: String, password: String): Boolean {
        val correctUsername = "admin"
        val hashedPassword = "\$2a\$12\$nb2drgFJPylkW9PfeQ01oeI0SFEH/XdBKMhJNpmLpACMqeTvVLUz2"

        // user:      admin
        // password:  contra123

        return username == correctUsername && BCrypt.checkpw(password, hashedPassword)
    }
    fun onAppExit() {
        closeButton.setOnAction {
            RoutesManager.onAppExit()
        }
    }

}