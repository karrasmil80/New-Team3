package dev.newteam.newteam3.controller

import dev.newteam.newteam3.routes.RoutesManager
import javafx.fxml.FXML
import javafx.scene.control.Button
import org.lighthousegames.logging.logging
import kotlin.math.log

private val logger = logging()
class NewTeamController {

    @FXML
    lateinit var campoDerechaButton: Button

    @FXML
    lateinit var eliminarButton: Button

    @FXML
    lateinit var editarAbajoButton: Button

    @FXML
    lateinit var anadirButton: Button

    @FXML
    lateinit var banquilloButton: Button

    fun initialize() {
        initEvents()
    }

    fun initEvents() {
        onBanquilloButtonClick()
        onAddButtonClick()
        onEditarAbajoButtonClick()
        onCampoDerechaButtonClick()
    }

    fun onBanquilloButtonClick() {
        banquilloButton.setOnAction {
            logger.debug { "Banquillo button clicked" }
            RoutesManager.initBanquilloNewTeamScreen()
        }
    }

    fun onAddButtonClick() {
        anadirButton.setOnAction {
            logger.debug { "Add banquillo button clicked" }
            RoutesManager.initModifyNewTeamScreen()
        }
    }

    fun onEditarAbajoButtonClick() {
        editarAbajoButton.setOnAction {
            logger.debug { "Editar abajo button clicked" }
            RoutesManager.initModifyNewTeamScreen()
        }
    }

    fun onCampoDerechaButtonClick() {
        campoDerechaButton.setOnAction {
            logger.debug { "Campo Derecha button clicked" }
            RoutesManager.escenaActiva.close()
            RoutesManager.initPlantillaMuppetStage()
        }
    }
}