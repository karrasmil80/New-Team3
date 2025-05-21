module dev.newteam.newteam3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens dev.newteam.newteam3 to javafx.fxml;
    exports dev.newteam.newteam3;
}