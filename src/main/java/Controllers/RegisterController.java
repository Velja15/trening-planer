package Controllers;

import Models.DatabaseConnector;
import Models.Korisnik;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    public Label username_lbl;
    public TextField username_field;
    public TextField password_field;
    public Button login_btn;
    public Label error_lbl;
    public FontAwesomeIconView back_btn;

    DatabaseConnector databaseConnector = new DatabaseConnector();


    public void napraviKorisnika()
    {
        Korisnik korisnik = new Korisnik(username_field.getText(),password_field.getText());



        if(username_field.getText().isEmpty() || password_field.getText().isEmpty() ) {
            Boolean uspesno = databaseConnector.insertKorisnik(korisnik);
            if(uspesno)
            {

                username_field.clear();
                password_field.clear();
                error_lbl.setText("");
                Stage stage = (Stage) login_btn.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showLoginWindow();
            }else{
                error_lbl.setText("Korisnik nije uspesno dodat!");
            }
        }else {
            error_lbl.setText("Sva polja moraju biti popunjena");
        }
    }

    public void goBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) back_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showStartWindow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
