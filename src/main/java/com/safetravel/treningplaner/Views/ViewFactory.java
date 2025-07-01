package com.safetravel.treningplaner.Views;

import com.safetravel.treningplaner.Controllers.MainController;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class ViewFactory {

    private AnchorPane treningplanerView;

    public ViewFactory() {

    }

    public AnchorPane getMainView() {
        if(treningplanerView == null){
            try{
                treningplanerView = new FXMLLoader(getClass().getResource("/FXML/Main.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return treningplanerView;
    }

    public void showLoginWindow()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        createStage(loader);
    }

    public void showRegisterWindow()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Register.fxml"));
        createStage(loader);
    }

    public void showMainWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Main.fxml"));

        createStage(loader);
    }

    private void createStage(FXMLLoader loader){
        Scene scene = null;
        try{
            scene = new Scene(loader.load());

        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/flash.png"))));
        stage.setResizable(false);
        stage.setTitle("TreningPlaner");
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }
}
