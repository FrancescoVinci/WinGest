package gestioneInfo;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import gestioneDashboard.Controller;
import gestioneRiepilogo.ControllerR;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

public class ControllerInfo {

    private Model model = new Model();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<RicaviRef> tabellaInfo;

    @FXML
    void initialize() {
        assert tabellaInfo != null : "fx:id=\"tabellaInfo\" was not injected: check your FXML file 'informationPanel.fxml'.";

        List<Ricavi> listRicavi = model.getSelectedInformationRicavi(ControllerR.globParrocchia,ControllerR.globRicavi);
        List<RicaviRef> l = Controller.convertFormatRicavi(listRicavi);


        TableColumn<RicaviRef, String> pri = new TableColumn<>("ParrocchiaID");
        TableColumn<RicaviRef, String> sec = new TableColumn<>("RicavoID");
        TableColumn<RicaviRef, String> ter = new TableColumn<>("Data");
        TableColumn<RicaviRef, String> quar = new TableColumn<>("Nome");
        TableColumn<RicaviRef, String> quin = new TableColumn<>("Descrizione");
        TableColumn<RicaviRef, String> sest = new TableColumn<>("Valore");

        TableColumn<RicaviRef, String> banca = new TableColumn<>("Banca");
        TableColumn<RicaviRef, String> cassa = new TableColumn<>("Cassa");

        PropertyValueFactory<RicaviRef, String> primoColFactory = new PropertyValueFactory<>("parrocchiaid");
        PropertyValueFactory<RicaviRef, String> secondoColFactory = new PropertyValueFactory<>("ricavoid");
        PropertyValueFactory<RicaviRef, String> terzoColFactory = new PropertyValueFactory<>("date");
        PropertyValueFactory<RicaviRef, String> quartoColFactory = new PropertyValueFactory<>("nome");
        PropertyValueFactory<RicaviRef, String> quintoColFactory = new PropertyValueFactory<>("descrizione");
        PropertyValueFactory<RicaviRef, String> sestoColFactory = new PropertyValueFactory<>("valorebanca");
        PropertyValueFactory<RicaviRef, String> settimoColFactory = new PropertyValueFactory<>("valorecassa");


        quar.prefWidthProperty().bind(tabellaInfo.widthProperty().multiply(0.17));
        quin.prefWidthProperty().bind(tabellaInfo.widthProperty().multiply(0.27));
        banca.prefWidthProperty().bind(tabellaInfo.widthProperty().multiply(0.1));
        cassa.prefWidthProperty().bind(tabellaInfo.widthProperty().multiply(0.1));

        sest.getColumns().addAll(banca,cassa);
        banca.setCellValueFactory(sestoColFactory);
        cassa.setCellValueFactory(settimoColFactory);


        pri.setCellValueFactory(primoColFactory);
        sec.setCellValueFactory(secondoColFactory);
        ter.setCellValueFactory(terzoColFactory);
        quar.setCellValueFactory(quartoColFactory);
        quin.setCellValueFactory(quintoColFactory);

        banca.setStyle("-fx-text-fill: green; -fx-alignment: CENTER-RIGHT");
        cassa.setStyle("-fx-text-fill: green; -fx-alignment: CENTER-RIGHT");

        tabellaInfo.getColumns().addAll(pri, sec, ter, quar, quin, sest);
        tabellaInfo.setItems(FXCollections.observableArrayList(l));
    }

    public void setModel(Model model) {
        this.model = model;

    }

}

