module com.niccolo.memory {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;


    opens com.niccolo.memory.controller to javafx.fxml;
    exports com.niccolo.memory;
    exports com.niccolo.memory.model;
}