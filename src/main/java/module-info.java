module com.niccolo.memory {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens com.niccolo.memory to javafx.fxml;
    exports com.niccolo.memory;
}