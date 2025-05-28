package dev.newteam.newteam3.controller

import dev.newteam.newteam3.routes.RoutesManager
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.fxml.FXML
import javafx.scene.control.ProgressBar
import javafx.util.Duration
import org.lighthousegames.logging.logging

private var logger = logging()
class SplashController {


    @FXML
    lateinit var loadingProgressBar: ProgressBar

    fun initialize() {
        logger.debug { "Cargando..." }
        loadingProgressBar.progress = 0.0
        var progress = 0.0

        val timeline = Timeline(
            KeyFrame(Duration.millis(50.0), {
                progress += 0.01
                loadingProgressBar.progress = progress

                if (progress >= 1.0) {
                    // Aquí llamas a la función para abrir tu ventana principal
                    RoutesManager.escenaActiva.close()
                    RoutesManager.initLoginStage(RoutesManager.escenaActiva)
                }
            })
        )
        timeline.cycleCount = 100 // 100 * 0.01 = 1.0 (100%)
        timeline.play()
    }
}