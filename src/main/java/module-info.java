module com.safetravel.treningplaner {
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires javafx.controls;
    requires java.desktop;
    requires java.sql;


    opens com.safetravel.treningplaner to javafx.fxml;
    opens com.safetravel.treningplaner.Controllers to javafx.fxml;
    exports com.safetravel.treningplaner.Controllers to javafx.fxml;
    exports com.safetravel.treningplaner.Models;
    exports com.safetravel.treningplaner.Views;
    exports com.safetravel.treningplaner;
}