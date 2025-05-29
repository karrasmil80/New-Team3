package dev.newteam.newteam3.controller

import com.vaadin.open.Open
import javafx.fxml.FXML
import javafx.scene.control.Hyperlink
import org.lighthousegames.logging.logging

private val logger = logging()
class AcercaDeController {

    @FXML
    lateinit var url4: Hyperlink
    @FXML
    lateinit var url3: Hyperlink
    @FXML
    lateinit var url2: Hyperlink
    @FXML
    lateinit var url1: Hyperlink

    fun initialize() {
        url1.setOnAction {
            logger.debug { "Redirigiendo a un sitio web" }
            val url = "https://github.com/karrasmil80"
            Open.open(url)
        }

        url2.setOnAction {
            logger.debug { "Redirigiendo a un sitio web" }
            val url = "https://github.com/PabloDLF06"
            Open.open(url)
        }

        url3.setOnAction {
            logger.debug { "Redirigiendo a un sitio web" }
            val url = "https://github.com/nicolasorteg"
            Open.open(url)
        }

        url4.setOnAction {
            logger.debug { "Redirigiendo a un sitio web" }
            val url = "https://github.com/Antukiller"
            Open.open(url)
        }
    }


}