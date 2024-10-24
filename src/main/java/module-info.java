module RotaCerta {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens org.example.rotacerta.screens to javafx.fxml;
    exports org.example.rotacerta.screens;
    exports org.example.rotacerta;
    opens org.example.rotacerta to javafx.fxml;
}