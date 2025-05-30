package dev.newteam.newteam3.controller

import dev.newteam.newteam3.config.Config
import dev.newteam.newteam3.plantilla.storage.general.PersonaStorageJson
import dev.newteam.newteam3.plantilla.storage.general.PersonaStorageZipImpl
import dev.newteam.newteam3.routes.RoutesManager
import javafx.fxml.FXML
import javafx.scene.control.Button
import org.lighthousegames.logging.logging
import java.io.File
import kotlin.math.log

private val logger = logging()
class NewTeamController {

    val storageZip = PersonaStorageZipImpl(
        config = Config(),
        storageJson = PersonaStorageJson()
    )

    val storageJson = PersonaStorageJson()

    @FXML
    lateinit var exportarButton: Button

    @FXML
    lateinit var importarButton: Button

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
        onImportarButtonClick()

    }

    fun initEvents() {
        onBanquilloButtonClick()
        onAddButtonClick()
        onEditarAbajoButtonClick()
        onCampoDerechaButtonClick()
        onImportarButtonClick()
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

    //REVISAR
    fun onImportarButtonClick() {
        importarButton.setOnAction {
            logger.debug { "Importar button clicked" }
            storageJson.readFromFile(File("data/NEW_TEAM.json"))
        }
    }


}