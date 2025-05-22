module dev.newteam.newteam3 {

    //DEPENDENCIAS
    //JAVA
    requires javafx.controls;
    requires javafx.fxml;

    //KOTLIN
    requires kotlin.reflect;

    //LOGGER
    requires logging.jvm;
    requires org.slf4j;

    //KOIN-
    requires koin.core.jvm;
    requires org.jdbi.v3.core;
    requires org.jdbi.v3.sqlobject;
    requires org.jdbi.v3.kotlin;
    requires com.github.benmanes.caffeine;
    requires kotlinx.serialization.core;


    //CARPETAS A ABRIR EN LA APP
    //APP
    opens dev.newteam.newteam3 to javafx.fxml;
    exports dev.newteam.newteam3 to javafx.graphics;

    //JDBI MANAGER
    opens dev.newteam.newteam3.database to javafx.fxml;
    exports dev.newteam.newteam3.database to javafx.graphics;

}