package dev.newteam.newteam3.controller

import org.mindrot.jbcrypt.BCrypt

class LoginController {
    fun login(username: String, password: String): Boolean {
        val correctUsername = "admin"
        val hashedPassword = "\$2a\$12\$nb2drgFJPylkW9PfeQ01oeI0SFEH/XdBKMhJNpmLpACMqeTvVLUz2"

        // user:      admin
        // password:  contra123

        return username == correctUsername && BCrypt.checkpw(password, hashedPassword)
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