package dev.newteam.newteam3

import dev.newteam.newteam3.config.Config
import dev.newteam.newteam3.di.AppModule
import dev.newteam.newteam3.plantilla.dao.PersonaDao
import dev.newteam.newteam3.routes.RoutesManager
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

private val logger = logging()
class HelloApplication : Application(), KoinComponent {

    init {
        println("HelloApplication init ${LocalDateTime.now()}")
        startKoin {
            printLogger()
            modules(AppModule)
        }
    }
    override fun start(stage: Stage) {
        logger.debug { "Iniciando New-Team APP" }
        RoutesManager.apply {
            app = this@HelloApplication
        }.run {
            initSplashScreen(stage)
        }
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)

}