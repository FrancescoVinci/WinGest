package gestioneImpostazioni;



import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Model;
import model.Parrochie;

import javax.swing.*;

public class ControllerImpostazioni {

    private Model model = new Model();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField testoAdd;

    @FXML
    private TextField testoDelete;

    @FXML
    private TextArea testoStampa;

    @FXML
    private Button buttonInserisci;

    @FXML
    private Button buttonElimina;

    @FXML
    void addParrocchia(ActionEvent event) {
        if (testoAdd.getText().length() == 0 && testoAdd.getText().length() < 255) {
            JOptionPane.showMessageDialog(null, "La parrocchia inserita supera i 255 caratteri o è nulla", "Error: nome troppo lungo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        model.addParrochie(testoAdd.getText());

        List<Parrochie> l = model.getParrocchie();
        String text = "";
        int i=1;
        for(Parrochie x : l){
            text += "Parrochia " + i + " : " + x.getNome() + "\n";
            i++;
        }
        testoStampa.setText(text);
    }

    @FXML
    void deleteParrocchia(ActionEvent event) {
        if (testoDelete.getText().length() == 0 && testoAdd.getText().length() < 255) {
            JOptionPane.showMessageDialog(null, "La parrocchia inserita supera i 255 caratteri o è nulla", "Error: nome troppo lungo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        model.deleteParrochie(testoDelete.getText());
        model.deleteCostiPerParrocchiaId(testoDelete.getText());
        model.deleteRicaviPerParrocchiaId(testoDelete.getText());

        List<Parrochie> l = model.getParrocchie();
        String text = "";
        int i=1;
        for(Parrochie x : l){
            text += "Parrochia " + i + " : " + x.getNome() + "\n";
            i++;
        }
        testoStampa.setText(text);
    }

    @FXML
    void initialize() {
        assert testoAdd != null : "fx:id=\"testoAdd\" was not injected: check your FXML file 'impostazioniPage.fxml'.";
        assert testoDelete != null : "fx:id=\"testoDelete\" was not injected: check your FXML file 'impostazioniPage.fxml'.";
        assert testoStampa != null : "fx:id=\"testoStampa\" was not injected: check your FXML file 'impostazioniPage.fxml'.";
        assert buttonInserisci != null : "fx:id=\"buttonInserisci\" was not injected: check your FXML file 'impostazioniPage.fxml'.";
        assert buttonElimina != null : "fx:id=\"buttonElimina\" was not injected: check your FXML file 'impostazioniPage.fxml'.";


        List<Parrochie> l = model.getParrocchie();
        String text = "";
        int i=1;
        for(Parrochie x : l){
            text += "Parrochia " + i + " : " + x.getNome() + "\n";
            i++;
        }
        testoStampa.setText(text);
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
