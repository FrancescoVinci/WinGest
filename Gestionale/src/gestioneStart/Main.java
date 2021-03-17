package gestioneStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Model;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Model model = new Model();
        ControllerStart controller;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("startPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        controller = loader.getController();
        controller.setModel(model);

        stage.getIcons().add(new Image("icon2.png"));
        stage.setTitle("Gestione");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}







