package gestioneInfo2;

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

public class ControllerInfo2 {

    private Model model = new Model();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<CostiRef> tabellaInfo;

    @FXML
    void initialize() {
        assert tabellaInfo != null : "fx:id=\"tabellaInfo\" was not injected: check your FXML file 'informationPanel2.fxml'.";

        List<Costi> listCosti = model.getSelectedInformationCosti(ControllerR.globParrocchia,ControllerR.globCosti);
        List<CostiRef> l = Controller.convertFormatCosti(listCosti);


        TableColumn<CostiRef, String> pri = new TableColumn<>("ParrocchiaID");
        TableColumn<CostiRef, String> sec = new TableColumn<>("CostoID");
        TableColumn<CostiRef, String> ter = new TableColumn<>("Data");
        TableColumn<CostiRef, String> quar = new TableColumn<>("Nome");
        TableColumn<CostiRef, String> quin = new TableColumn<>("Descrizione");
        TableColumn<CostiRef, String> sest = new TableColumn<>("Valore");

        TableColumn<CostiRef, String> banca = new TableColumn<>("Banca");
        TableColumn<CostiRef, String> cassa = new TableColumn<>("Cassa");

        PropertyValueFactory<CostiRef, String> primoColFactory = new PropertyValueFactory<>("parrocchiaid");
        PropertyValueFactory<CostiRef, String> secondoColFactory = new PropertyValueFactory<>("costoid");
        PropertyValueFactory<CostiRef, String> terzoColFactory = new PropertyValueFactory<>("date");
        PropertyValueFactory<CostiRef, String> quartoColFactory = new PropertyValueFactory<>("nome");
        PropertyValueFactory<CostiRef, String> quintoColFactory = new PropertyValueFactory<>("descrizione");
        PropertyValueFactory<CostiRef, String> sestoColFactory = new PropertyValueFactory<>("valorebanca");
        PropertyValueFactory<CostiRef, String> settimoColFactory = new PropertyValueFactory<>("valorecassa");


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

        banca.setStyle("-fx-text-fill: red; -fx-alignment: CENTER-RIGHT");
        cassa.setStyle("-fx-text-fill: red; -fx-alignment: CENTER-RIGHT");

        tabellaInfo.getColumns().addAll(pri, sec, ter, quar, quin, sest);
        tabellaInfo.setItems(FXCollections.observableArrayList(l));
    }

    public void setModel(Model model) {
        this.model = model;

    }

}
