package com.safetravel.treningplaner.Controllers;

import com.safetravel.treningplaner.Models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public Label username_lbl;
    public TextField username_field;
    public TextField password_field;
    public Button login_btn;
    public Label error_lbl ;
    public Button register_btn;

    @FXML
    public FontAwesomeIconView iconView;

    public void promeniIkonu() {
        iconView.setGlyphName("BOLT");
    }

    public void goBack(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) register_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showRegisterWindow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        promeniIkonu();
        login_btn.setOnAction(event -> onLogin());
    }

    private void onLogin() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().proveriKorisnikUnos(username_field.getText(), password_field.getText());
        if(Model.getInstance().getKorisnikUspesnoLogin())
        {
            Model.getInstance().getViewFactory().showMainWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        }else{
            username_field.setText("");
            password_field.setText("");
            error_lbl.setText("Netacan unos!");
        }
    }
}
