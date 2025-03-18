module RotaCerta {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    exports org.example.rotacerta;
    opens org.example.rotacerta to javafx.fxml;
    exports org.example.rotacerta.config;
    opens org.example.rotacerta.config to javafx.fxml;
    exports org.example.rotacerta.service;
    opens org.example.rotacerta.service to javafx.fxml;
    exports org.example.rotacerta.controller;
    opens org.example.rotacerta.controller to javafx.fxml;
    exports org.example.rotacerta.Model;
    opens org.example.rotacerta.Model to javafx.base;
}