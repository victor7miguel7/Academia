module com.academia {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens gui to javafx.fxml;
    exports gui;

    opens models to javafx.fxml;
    exports models;
}