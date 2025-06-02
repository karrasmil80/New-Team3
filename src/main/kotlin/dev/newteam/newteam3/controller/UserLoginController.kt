package dev.newteam.newteam3.controller

import dev.newteam.newteam3.routes.RoutesManager
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import org.lighthousegames.logging.logging

private val logger = logging()

class UserLoginController {

    @FXML
    lateinit var usernameTextField: TextField

    @FXML
    lateinit var passwordTextField: PasswordField

    @FXML
    lateinit var enterButton: Button

    @FXML
    lateinit var cancelButton: Button


    fun initialize() {
        enterButton.setOnAction {
            val username = usernameTextField.text
            val password = passwordTextField.text

            logger.debug { "Acceso libre como usuario: $username" }
            RoutesManager.initPlantillaStage()
            enterButton.scene.window.hide()
        }

        /*
        cancelButton.setOnAction {
            logger.debug { "Cancelar button clicked" }
            RoutesManager.initLoginAdminStage()
            cancelButton.scene.window.hide()
        }
         */
    }

}

