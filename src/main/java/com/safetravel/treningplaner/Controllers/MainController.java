package com.safetravel.treningplaner.Controllers;

import com.safetravel.treningplaner.Models.DatabaseConnector;
import com.safetravel.treningplaner.Models.Trening;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;

import java.net.URL;
import java.sql.ResultSet;
import java.util.*;

public class MainController implements Initializable {

    @FXML
    private ComboBox<String> daySelector;
    @FXML
    private ListView<String> listView;
    private final Map<String, List<String>> exercisesByDay = new HashMap<>();
    @FXML
    private Button addButton;
    @FXML
    private TextField imefield;
    @FXML
    private ComboBox<String> dancombobox;
    @FXML
    private ComboBox<String> tipvezbecombobox;
    @FXML
    private ComboBox<String> zavrsenocombobox;
    @FXML
    public Label error_lbl ;
    @FXML
    private Button obrisiButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        daySelector.getItems().addAll(
                "Ponedeljak",
                "Utorak",
                "Sreda",
                "Cetvrtak",
                "Petak",
                "Subota",
                "Nedelja"
        );
        dancombobox.getItems().addAll(
                "Ponedeljak",
                "Utorak",
                "Sreda",
                "Cetvrtak",
                "Petak",
                "Subota",
                "Nedelja"
        );
        tipvezbecombobox.getItems().addAll(
                "Vezba Snage",
                "Kardio"
        );
        zavrsenocombobox.getItems().addAll(
                "Zavrseno",
                "Nije zavrseno"
        );


        daySelector.setValue("Ponedeljak");
        ucitajVezbeizBaze("Ponedeljak");
        daySelector.setOnAction(e -> ucitajVezbeizBaze(daySelector.getSelectionModel().getSelectedItem()));
        addButton.setOnAction(e -> unesiVezbu());
        zavrsenocombobox.setValue("Zavrseno");
        tipvezbecombobox.setValue("Kardio");
        dancombobox.setValue("Ponedeljak");
        error_lbl.setText("");
        obrisiButton.setOnAction(e -> obrisiVezbu());



    }

    private void obrisiVezbu(){
        String red = listView.getSelectionModel().getSelectedItem();
        String dan = daySelector.getValue();

        if(red == null || dan == null){
            error_lbl.setText("Nijedna vezba nije selektovana");
            return;
        }
        String[] delovi = red.split(" \\(");
        String naziv = delovi[0];
        boolean uspesno = DatabaseConnector.obrisiVezbu(naziv,dan);
        if(uspesno){
            error_lbl.setText("");
            ucitajVezbeizBaze(daySelector.getSelectionModel().getSelectedItem());
        }else{
            error_lbl.setText("Vezba nije uspesno obrisana!");
        }
    }
    private void unesiVezbu() {
        int dan = 0;
        int tip = 0;
        int zavrsen = 0;
        try{
            if(imefield.getText().isEmpty()){
                imefield.setText("");
                error_lbl.setText("Sve mora biti popunjeno!");
            }else {
                if(dancombobox.getSelectionModel().getSelectedItem().equals("Ponedeljak")){
                    dan = 1;
                }else if(dancombobox.getSelectionModel().getSelectedItem().equals("Utorak")){
                    dan = 2;
                }else if(dancombobox.getSelectionModel().getSelectedItem().equals("Sreda")){
                    dan = 3;
                }else if(dancombobox.getSelectionModel().getSelectedItem().equals("Cetvrtak")){
                    dan = 4;
                }else if(dancombobox.getSelectionModel().getSelectedItem().equals("Petak")){
                    dan = 5;
                }else if(dancombobox.getSelectionModel().getSelectedItem().equals("Subota")){
                    dan = 6;
                }else if(dancombobox.getSelectionModel().getSelectedItem().equals("Nedelja")){
                    dan = 7;
                }else {
                    dan = 0;
                }
                if(tipvezbecombobox.getSelectionModel().getSelectedItem().equals("Kardio"))
                {
                    tip = 2;
                }else if(tipvezbecombobox.getSelectionModel().getSelectedItem().equals("Vezba Snage"))
                {
                    tip = 1;
                }else {
                    tip = 0;
                }
                if(zavrsenocombobox.getSelectionModel().getSelectedItem().equals("Zavrseno"))
                {
                    zavrsen = 1;
                }else if(zavrsenocombobox.getSelectionModel().getSelectedItem().equals("Nije zavrseno")){
                    zavrsen = 2;
                }else {
                    zavrsen = 0;
                }

                Trening trening = new Trening(dan,imefield.getText(),tip,zavrsen);
                Boolean uspesno = DatabaseConnector.insertTrening(trening);
                if(uspesno){
                    ucitajVezbeizBaze(dancombobox.getSelectionModel().getSelectedItem());
                }
                else{
                    error_lbl.setText("Dodavanje nije uspesno!");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ucitajVezbeizBaze(String dan)
    {
        try{
            ResultSet rs = DatabaseConnector.getVezbe(dan);
            String tip_novi = "";
            List<String> vezbe = new ArrayList<>();

            while(rs != null && rs.next()){
                String naziv = rs.getString("ime");
                String tip = rs.getString("tip_vezbe");
                if(tip.equals("1")){
                    tip_novi = "Vezba snage";
                }else if(tip.equals("2")){
                     tip_novi = "Kardio";
                }

                String prikaz = naziv + " (" + tip_novi + ")";
                vezbe.add(prikaz);
            }
            listView.getItems().clear();
            listView.setItems(FXCollections.observableArrayList(vezbe));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
