module com.marijmokoginta.shopmanagement {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires fontawesomefx;

    opens com.marijmokoginta.shopmanagement to javafx.fxml;
    exports com.marijmokoginta.shopmanagement;
}