module dev.newteam.newteam3 {

    //DEPENDENCIAS
    //JAVA
    requires javafx.controls;
    requires javafx.fxml;

    //KOTLIN
    requires kotlin.stdlib;
    requires kotlin.reflect;

    //LOGGER
    requires logging.jvm;
    requires org.slf4j;

    //KOIN-
    requires koin.core.jvm;


    //CARPETAS A ABRIR EN LA APP
    //APP
    opens dev.newteam.newteam3 to javafx.fxml;
    exports dev.newteam.newteam3 to javafx.graphics;

}