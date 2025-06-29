module com.safetravel.treningplaner {
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires javafx.controls;
    requires java.desktop;
    requires java.sql;


    opens com.safetravel.treningplaner to javafx.fxml;
    exports com.safetravel.treningplaner;
}