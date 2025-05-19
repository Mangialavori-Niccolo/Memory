module com.niccolo.memory {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens com.niccolo.memory to javafx.fxml;
    exports com.niccolo.memory;
    exports com.niccolo.memory.model;
    opens com.niccolo.memory.model to javafx.fxml;
}