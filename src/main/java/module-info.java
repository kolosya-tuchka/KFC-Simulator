module com.example.kfcsimulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires java.desktop;
    requires javafx.graphics;

    opens com.example.kfcsimulator to javafx.fxml;
    exports NewDayNewGame;
    opens NewDayNewGame to javafx.fxml;
}