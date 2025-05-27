package dev.newteam.newteam3.controller

import dev.newteam.newteam3.routes.RoutesManager
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextField
import org.lighthousegames.logging.logging

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
        loginButton.setOnAction { RoutesManager.initPlantillaStage() }
    }
}