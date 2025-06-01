package dev.newteam.newteam3.controller

import dev.newteam.newteam3.plantilla.models.Persona
import dev.newteam.newteam3.routes.RoutesManager
import javafx.fxml.FXML
import javafx.scene.control.Button
import org.lighthousegames.logging.logging

private val logger = logging()
class MuppetsController {

    @FXML
    lateinit var ayudaButton: Button

    @FXML
    lateinit var campoIzquierdaButton: Button

    @FXML
    lateinit var banquilloButton: Button

    @FXML
    lateinit var anadirButton: Button

    @FXML
    lateinit var editarAbajoButton: Button

    var persona : Persona? = null

    fun initialize() {
        initEvents()
        onAyudaButtonClick()
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

    fun onAyudaButtonClick() {
        ayudaButton.setOnAction {
            RoutesManager.initAcercaDe()
            AcercaDeController()
        }
    }

    fun cargarEnModifyView(persona: Persona) {
        persona.let {
            
        }
    }
}