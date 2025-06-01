package dev.newteam.newteam3.controller

import dev.newteam.newteam3.config.Config
import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona
import dev.newteam.newteam3.plantilla.storage.general.PersonaStorageCsv
import dev.newteam.newteam3.plantilla.storage.general.PersonaStorageJson
import dev.newteam.newteam3.plantilla.storage.general.PersonaStorageZipImpl
import dev.newteam.newteam3.routes.RoutesManager
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.stage.FileChooser
import javafx.stage.Stage
import org.lighthousegames.logging.logging
import java.io.File

private val logger = logging()
class NewTeamController {

    @FXML
    lateinit var nombresEntrenadoresField: TableColumn<Entrenador, String>

    @FXML
    lateinit var tableViewNewTeamEntrenadores: TableView<Entrenador>

    @FXML
    lateinit var nombreApellidoCell: TableColumn<Jugador, String>

    @FXML
    lateinit var dorsalCell: TableColumn<Jugador, String>

    @FXML
    lateinit var tableViewNewTeam: TableView<Jugador>

    val storageZip = PersonaStorageZipImpl(
        config = Config(),
        storageJson = PersonaStorageJson()
    )

    val storageJson = PersonaStorageJson()

    val csvStorage = PersonaStorageCsv()

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
        initDefaultValues()
        initEvents()

    }

    fun initEvents() {
        onBanquilloButtonClick()
        onAddButtonClick()
        onEditarAbajoButtonClick()
        onCampoDerechaButtonClick()
        onImportarButtonClick()
        onExportarButtonClick()
        onEliminarButtonClick()
    }

    fun initDefaultValues() {
        dorsalCell.cellValueFactory = PropertyValueFactory("dorsal")
        nombreApellidoCell.cellValueFactory = PropertyValueFactory("nombreCompleto")
        nombresEntrenadoresField.cellValueFactory = PropertyValueFactory("nombreCompleto")
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
            RoutesManager.initModifyNewTeamScreen(null)
        }
    }


    fun onEditarAbajoButtonClick() {
        editarAbajoButton.setOnAction {
            logger.debug { "Editar abajo button clicked" }
            val jugadorSeleccionado = tableViewNewTeam.selectionModel.selectedItem
            val entrenadorSeleccionado = tableViewNewTeamEntrenadores.selectionModel.selectedItem

            val personaSeleccionada: Persona? = jugadorSeleccionado ?: entrenadorSeleccionado

            if (personaSeleccionada != null) {
                RoutesManager.initModifyNewTeamScreen(personaSeleccionada)
            } else {
                Alert(Alert.AlertType.ERROR).apply {
                    title = "Error"
                    headerText = "Debes seleccionar un jugador o entrenador"
                    contentText = "Selecciona uno para poder editarlo"
                }.showAndWait()
            }
            tableViewNewTeam.refresh()
            tableViewNewTeamEntrenadores.refresh()
        }
    }

    fun onGuardarButtonClick() {
        logger.info { "Guardar button clicked" }
        anadirButton.setOnAction {
        }

    }

    fun onCampoDerechaButtonClick() {
        campoDerechaButton.setOnAction {
            logger.debug { "Campo Derecha button clicked" }
            RoutesManager.escenaActiva.close()
            RoutesManager.initPlantillaMuppetStage()
        }
    }

    fun abrirExploradorArchivos(stage: Stage): File? {
        val fileChooser = FileChooser()
        fileChooser.title = "Selecciona un archivo CSV"
        fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("Archivos CSV", "*.csv"))
        return fileChooser.showOpenDialog(stage)
    }

    fun onEliminarButtonClick() {
        eliminarButton.setOnAction {
            val selectedJugador = tableViewNewTeam.selectionModel.selectedItem
            val selectedEntrenador = tableViewNewTeamEntrenadores.selectionModel.selectedItem
            if (selectedJugador != null || selectedEntrenador != null) {
                tableViewNewTeam.items.remove(selectedJugador)
                tableViewNewTeamEntrenadores.items.remove(selectedEntrenador)
                logger.debug { "Jugador eliminado: $selectedJugador" }
            } else {
                Alert(Alert.AlertType.WARNING, "Por favor, selecciona un jugador o entrenador para eliminar.").showAndWait()
            }
        }
    }

    fun onImportarButtonClick() {
        importarButton.setOnAction {
            logger.debug { "Importar button clicked" }

            val stage = importarButton.scene.window as Stage

            val archivoSeleccionado = abrirExploradorArchivos(stage)

            if (archivoSeleccionado != null) {
                val resultado = csvStorage.readFromFile(archivoSeleccionado)
                if (resultado.isOk) {
                    val personas = resultado.value
                    val jugadores = personas.filterIsInstance<Jugador>()
                    val entrenadores = personas.filterIsInstance<Entrenador>()
                    tableViewNewTeam.items = FXCollections.observableArrayList(jugadores)
                    tableViewNewTeamEntrenadores.items = FXCollections.observableArrayList(entrenadores)
                } else {
                    val error = resultado.error
                    logger.error { "Error al importar: ${error.message}" }
                    Alert(Alert.AlertType.ERROR, "Error al importar: ${error.message}").showAndWait()
                }
            } else {
                logger.debug { "Importación cancelada por el usuario" }
            }
        }
    }

    fun onExportarButtonClick() {
        exportarButton.setOnAction {
            val personas = tableViewNewTeam.items.toList() // Copia segura de los datos

            val fileChooser = FileChooser()
            fileChooser.title = "Guardar archivo CSV"
            fileChooser.initialFileName = "export.csv"
            fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("CSV Files", "*.csv"))
            val file = fileChooser.showSaveDialog(null)

            if (file != null) {
                val result = csvStorage.writeToFile(file, personas)
                if (result.isOk) {
                    Alert(Alert.AlertType.INFORMATION, "Exportación completada correctamente.").showAndWait()
                    logger.debug { "Archivo exportado a: ${file.absolutePath}" }
                } else {
                    Alert(Alert.AlertType.ERROR, "Error al exportar: ${result.error.message}").showAndWait()
                    logger.error { "Error al exportar: ${result.error.message}" }
                }
            }
        }
    }

    fun abrirFormularioModificacion(persona: Persona) {
        val loader = FXMLLoader(javaClass.getResource("NewTeamModify.fxml"))
        val root = loader.load<Parent>()
        val modifyController = loader.getController<NewTeamModifyController>()
        modifyController.mainController = this
        modifyController.setPersona(persona)

        val stage = Stage()
        stage.scene = Scene(root)
        stage.show()
    }

    fun addJugador(jugador: Jugador) {
        if (tableViewNewTeam.items == null) {
            tableViewNewTeam.items = FXCollections.observableArrayList()
        }
        tableViewNewTeam.items.add(jugador)
    }
}