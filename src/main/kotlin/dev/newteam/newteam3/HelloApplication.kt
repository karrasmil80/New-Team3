package dev.newteam.newteam3

import dev.newteam.newteam3.di.AppModule
import dev.newteam.newteam3.plantilla.dao.PersonaDao
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin
import java.time.LocalDateTime

class HelloApplication : Application(), KoinComponent {

    init {
        println("HelloApplication init ${LocalDateTime.now()}")
        startKoin {
            printLogger()
            modules(AppModule)
        }
    }
    override fun start(stage: Stage) {
        val resource = javaClass.getResource("views/hello-view.fxml")
        println("FXML encontrado? ${resource != null} → $resource")
        requireNotNull(resource) { "No se encontró el FXML en el classpath" }

        val fxmlLoader = FXMLLoader(resource)

        val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
        stage.title = "Hello!"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)

}