package gestioneStart;

import gestioneDashboard.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import gestioneDashboard.Controller;
import gestioneImpostazioni.ControllerImpostazioni;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Model;
import model.Parrochie;

import javax.swing.*;

public class ControllerStart {

    private Model model = new Model();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonImpostazioni;

    @FXML
    private Button buttonAvvia;

    @FXML
    void startApplication(ActionEvent event) throws IOException {

        List<Parrochie> l = model.getParrocchie();
        if(!l.isEmpty()) {
            System.out.println(l);

            Controller controller;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            controller = loader.getController();
            controller.setModel(model);

            stage.getIcons().add(new Image("icon2.png"));
            stage.setTitle("Gestione");
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }else{
            JOptionPane.showMessageDialog(null, "Prima di avviare il programma è necessario aggiungere una o piu parrochie da gestire.\n Per aggiungre le parrocchie andare in Impostazioni.", "Attenzione", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    @FXML
    void startSettings(ActionEvent event) throws IOException {
        ControllerImpostazioni controller;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("impostazioniPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        controller = loader.getController();
        controller.setModel(model);

        stage.getIcons().add(new Image("icon2.png"));
        stage.setTitle("Gestione");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    void initialize() {
        assert buttonImpostazioni != null : "fx:id=\"buttonImpostazioni\" was not injected: check your FXML file 'startPage.fxml'.";
        assert buttonAvvia != null : "fx:id=\"buttonAvvia\" was not injected: check your FXML file 'startPage.fxml'.";

    }

    public void setModel(Model model) {
        this.model = model;
    }
}
