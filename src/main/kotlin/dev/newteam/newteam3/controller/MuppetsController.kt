package dev.newteam.newteam3.controller

import dev.newteam.newteam3.routes.RoutesManager
import javafx.fxml.FXML
import javafx.scene.control.Button
import org.lighthousegames.logging.logging

private val logger = logging()
class MuppetsController {

    @FXML
    lateinit var campoIzquierdaButton: Button

    @FXML
    lateinit var banquilloButton: Button

    @FXML
    lateinit var anadirButton: Button

    @FXML
    lateinit var editarAbajoButton: Button

    fun initialize() {
        initEvents()
    }

    fun initEvents() {
        onBanquilloButtonClick()
        onAddButtonClick()
        onEditarAbajoButtonClick()
        onCampoIzquierdaButtonClick()
    }

    fun onBanquilloButtonClick() {
        banquilloButton.setOnAction {
            logger.debug { "Banquillo button clicked" }
            RoutesManager.initBanquilloMuppetScreen()
        }
    }

    fun onAddButtonClick() {
        anadirButton.setOnAction {
            logger.debug { "Add banquillo button clicked" }
            RoutesManager.initModifyMuppetScreen()
        }
    }

    fun onEditarAbajoButtonClick() {
        editarAbajoButton.setOnAction {
            logger.debug { "Editar abajo button clicked" }
            RoutesManager.initModifyMuppetScreen()
        }
    }

    fun onCampoIzquierdaButtonClick() {
        campoIzquierdaButton.setOnAction {
            logger.debug { "Campo Derecha button clicked" }
            RoutesManager.escenaActiva.close()
            RoutesManager.initPlantillaStage()
        }
    }
}