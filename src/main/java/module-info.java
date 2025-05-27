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

    //KOIN
    requires koin.core.jvm;
    requires org.jdbi.v3.core;
    requires org.jdbi.v3.sqlobject;
    requires org.jdbi.v3.kotlin;
    requires com.github.benmanes.caffeine;
    requires kotlinx.serialization.core;
    requires kotlin.result.jvm;
    requires org.jdbi.v3.sqlobject.kotlin;


    //CARPETAS A ABRIR EN LA APP
    //APP
    opens dev.newteam.newteam3 to javafx.fxml;
    exports dev.newteam.newteam3 to javafx.graphics;

    //JDBI MANAGER
    opens dev.newteam.newteam3.database to javafx.fxml;
    exports dev.newteam.newteam3.database to javafx.graphics;

    //DAO
    opens dev.newteam.newteam3.plantilla.dao to org.jdbi.v3.core;
    exports dev.newteam.newteam3.plantilla.dao to kotlin.reflect;

    //MAIN CONTOLLER
    opens dev.newteam.newteam3.controller to javafx.fxml;
    exports dev.newteam.newteam3.controller to javafx.graphics;

    //ROUTES MANAGER
    opens dev.newteam.newteam3.routes to javafx.fxml;
    exports dev.newteam.newteam3.routes to javafx.graphics;


}

