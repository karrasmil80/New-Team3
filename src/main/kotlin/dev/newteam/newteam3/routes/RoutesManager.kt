package dev.newteam.newteam3.routes

import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.layout.Pane
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.stage.WindowEvent
import org.lighthousegames.logging.logging
import java.io.InputStream
import java.net.URL
import java.util.*
import kotlin.collections.HashMap

private val logger = logging()

object RoutesManager {
    lateinit var escenaPrincipal : Stage
    private lateinit var _escenarioActivo : Stage
    val escenaActiva : Stage
        get() = _escenarioActivo
    lateinit var app : Application

    private var sceneMap : HashMap<String, Pane> = HashMap()

    enum class View(val fxmlPath : String) {
        PLANTILLA_MUPPET("views/muppets-view.fxml"),
        BANQUILLO_MUPPET("views/muppets-banquillo-view.fxml"),
        PLANTILLA_NEWTEAM("views/newteam-view.fxml"),
        BANQUILLO_NEWTEAM("views/newteam-banquillo-view.fxml"),
        LOGIN("views/login-view.fxml"),
        HELP(""),
        SPLASH("views/splash-view.fxml"),
        MODIFY_NEWTEAM("views/newteam-modify-view.fxml"),
        MODIFY_MUPPET("views/muppets-modify-view.fxml"),
    }

    init {
        logger.debug { "Iniciando Routes Manager" }
        Locale.setDefault(Locale.forLanguageTag("es-ES"))
    }

    fun getResourceAsStream(resource : String) : InputStream {
        return app::class.java.getResourceAsStream(resource)
            ?:throw RuntimeException("Resource como stream : $resource no encontrado")
    }

    fun getResource(resource : String) : URL {
        return app::class.java.getResource(resource)
            ?: throw RuntimeException("Resource $resource no encontrado")
    }

    fun initLoginStage(mainStage: Stage) {
        logger.debug { "Iniciando ventana de login" }
        val fxmlLoader = FXMLLoader(getResource(View.LOGIN.fxmlPath))
        val root = fxmlLoader.load<Pane>()
        val newScene = Scene(root, 660.0, 440.0)
        mainStage.apply {
            title = "Login New Team"
            scene = newScene
            centerOnScreen()
            isResizable = false

        }.show()

        escenaPrincipal = mainStage
        _escenarioActivo = mainStage
    }

    fun initPlantillaStage() {
        logger.debug { "Cargando escena new team" }
        val fxmlLoader = FXMLLoader(getResource(View.PLANTILLA_NEWTEAM.fxmlPath))
        val root = fxmlLoader.load<Pane>()
        val newScene = Scene(root, 1400.0, 780.0)
        Stage().apply {
            title = "New Team APP"
            scene = newScene
            centerOnScreen()
            isResizable = false

        }.show()
    }

    fun initPlantillaMuppetStage() {
        logger.debug { "Cargando escena muppet" }
        val fxmlLoader = FXMLLoader(getResource(View.PLANTILLA_MUPPET.fxmlPath))
        val root = fxmlLoader.load<Pane>()
        val newScene = Scene(root, 1400.0, 780.0)
        Stage().apply {
            title = "New Team APP"
            scene = newScene
            centerOnScreen()
            isResizable = false

        }.show()
    }

    fun initAcercaDe() {
        logger.debug { "Cargando acerca de" }
        val fxmlLoader = FXMLLoader(getResource(View.HELP.fxmlPath))
        val root = fxmlLoader.load<Pane>()
        val newScene = Scene(root, 700.0, 700.0)
        Stage().apply {
            title = "New Team acerca de"
            scene = newScene
            centerOnScreen()
            isResizable = false
            initModality(Modality.WINDOW_MODAL)

        }.show()
    }

    fun initSplashScreen(stage: Stage) {
        logger.debug { "Iniciando Splash screen" }
        val fxmlLoader = FXMLLoader(getResource(View.SPLASH.fxmlPath))
        val root = fxmlLoader.load<Pane>()
        val newScene = Scene(root, 780.0, 770.0)

        escenaPrincipal = stage
        _escenarioActivo = stage

        stage.apply {
            title = "New Team Splash Screen"
            scene = newScene
            centerOnScreen()
            isResizable = false

        }.show()
    }

    fun initBanquilloNewTeamScreen() {
        logger.debug { "Iniciando Banquillo New Team" }
        val fxmlLoader = FXMLLoader(getResource(View.BANQUILLO_NEWTEAM.fxmlPath))
        val root = fxmlLoader.load<Pane>()
        val newScene = Scene(root, 600.0, 870.0)
        Stage().apply {
            title = "New Team Banquillo"
            scene = newScene
            centerOnScreen()
            isResizable = false
        }.show()
    }

    fun initBanquilloMuppetScreen() {
        logger.debug { "Iniciando Banquillo Muppet" }
        val fxmlLoader = FXMLLoader(getResource(View.BANQUILLO_MUPPET.fxmlPath))
        val root = fxmlLoader.load<Pane>()
        val newScene = Scene(root, 600.0, 870.0)
        Stage().apply {
            title = "New Team Banquillo"
            scene = newScene
            centerOnScreen()
            isResizable = false
        }.show()
    }

    fun initModifyNewTeamScreen() {
        logger.debug { "Iniciando ModifyScreen New Team" }
        val fxmlLoader = FXMLLoader(getResource(View.MODIFY_NEWTEAM.fxmlPath))
        val root = fxmlLoader.load<Pane>()
        val newScene = Scene(root, 895.0, 570.0)
        Stage().apply {
            title = "New Team ModifyScreen"
            scene = newScene
            centerOnScreen()
            isResizable = false
        }.show()
    }

    fun initModifyMuppetScreen() {
        logger.debug { "Iniciando ModifyScreen Muppet" }
        val fxmlLoader = FXMLLoader(getResource(View.MODIFY_MUPPET.fxmlPath))
        val root = fxmlLoader.load<Pane>()
        val newScene = Scene(root, 895.0, 570.0)
        Stage().apply {
            title = "Muppet ModifyScreen"
            scene = newScene
            centerOnScreen()
            isResizable = false
        }.show()
    }

    fun onAppExit(
        title : String = "¿Salir de la Aplicacion de New-Team?",
        header : String = "¿Seguro que desea salir de la aplicación?",
        content : String = "Se perderán los datos no guardados",
        event: WindowEvent? = null
    ){
        logger.debug { "Cerrando aplicacion" }
        Alert(Alert.AlertType.CONFIRMATION).apply {
            this.title = title
            this.headerText = header
            this.contentText = content
        }.showAndWait().ifPresent { opcion ->
            if (opcion == ButtonType.OK) {
                Platform.exit()
            } else {
                event?.consume()
            }
        }
    }

}