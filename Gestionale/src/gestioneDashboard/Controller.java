package gestioneDashboard;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


import gestioneRiepilogo.ControllerR;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.*;

import javax.swing.*;


public class Controller {

    private Model model = new Model();
    private final String[] items = {"COSTI", "RICAVI"};
    private final String[] items2 = {"BANCA", "CASSA"};
    private String[] costi = {
            "Acqua",
            "Acquisto apparecchi tecnologici e di stampa",
            "Acquisto candele",
            "Altre assicurazioni",
            "ALTRI COSTI",
            "Assicurazioni degli immobili",
            "Assicurazioni RC - Infortuni",
            "Cancelleria",
            "Compensi professionali",
            "Gas",
            "Interessi e oneri bancari su c/c",
            "Lavoro di restauro canonica, patronato, altre strutture",
            "Libri liturgici",
            "Luce",
            "Manutenzioni ordinarie",
            "Offerte a celebranti occasionali",
            "Offerte per carità (poveri, profughi, missioni)",
            "Ostie e Vino",
            "Paramenti e suppellettili sacre",
            "Remunerazione Parroco, vicario..",
            "Remunerazione personale laico (sacrestano, altro…)",
            "Rinnovo o costruzione impianti di luce, termico…",
            "Spese per la pulizia canonica, chiesa, patronato",
            "Stampa e Libri",
            "Tassa diocesana",
            "Tasse e imposte sugli Immobili istituzionali",
            "Tasse e imposte sugli immobili locati",
            "Tasse sui redditi",
            "Telefono"};

    private String[] ricavi = {
            "Entrate questue sante messe",
            "Altre entrate",
            "Entr. affitti da terreni",
            "Entr. affitti per uso patronato e altre strutture",
            "Entr. Contributi da CEI",
            "Entr. Contributi da Ente Diocesi di Venezia",
            "Entr. Contributi da Enti pubblici",
            "Entr. Contributi straordinari da Privati",
            "Entr. Offerte straordinarie",
            "Entr. Raccolte straordinarie",
            "Entr.affitti da Immobili commerciali",
            "Entr.da Prestiti da diversi",
            "Entrate altre feste o ricorrenze",
            "Entrate benedizione famiglie",
            "Entrate candele e lumini",
            "Entrate da assicurazioni",
            "Entrate da associazioni parrocchiali",
            "Entrate da attività pastorali",
            "Entrate funerali",
            "Entrate interessi su conto corrente",
            "Entrate nette da venditadi mobili ed immobili",
            "Entrate per patronato",
            "Entrate sacramenti I.C e matrimoni",
            "Entrate varie",
            "Entrte affitti da Immobili abitativi",
            "Eredità e donazioni"
    };

    private static boolean flag1 = true;
    private static boolean flag2 = true;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> tipo;

    @FXML
    private TextField descrizione;

    @FXML
    private TextField valore;

    @FXML
    private TextField data;

    @FXML
    private Button aggiungi;

    @FXML
    private ComboBox<String> tipo2;

    @FXML
    private ComboBox<String> tipo3;

    @FXML
    private TextField id;

    @FXML
    private TableView<CostiRef> tabellaCosti;

    @FXML
    private TableView<RicaviRef> tabellaRicavi;

    @FXML
    private ComboBox<String> parrocchia;

    @FXML
    private ComboBox<String> tabellaVisualizzata;

    @FXML
    private Button riepilogo;

    @FXML
    private ComboBox<String> bancaCassa;

    @FXML
    void changeTable(ActionEvent event) {
        if(tabellaVisualizzata.getValue().equals("TUTTO")){
            createTableCosti();
            createTableRicavi();
            return;
        }
        createTableCostiPerParrocchia(tabellaVisualizzata.getValue());
        createTableRicaviPerParrocchia(tabellaVisualizzata.getValue());

    }

    @FXML
    void addElement(ActionEvent event) {

        if (parrocchia.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Seleziona il nome della parrocchia in 'Parrocchia'", "Error: 'Scegli tipologia' è nullo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (tipo.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Inserire COSTO o RICAVO in 'Scegli tipologia'", "Error: 'Scegli tipologia' è nullo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (tipo2.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Inserire tipologia del COSTO o del RICAVO ", "Error: Costo o Ricavo non specificati", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (bancaCassa.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Inserire tipologia del COSTO o del RICAVO ", "Error: Costo o Ricavo non specificati", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (descrizione.getText().length() == 0 && descrizione.getText().length() < 255) {
            JOptionPane.showMessageDialog(null, "La descrizione inserita supera i 255 caratteri o è nulla", "Error: descrizione troppo lunga", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        double val;
        try {
            val = Double.parseDouble(valore.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Inserire un valore numerico nel campo 'Valore'", "Error: not a number", JOptionPane.INFORMATION_MESSAGE);
            return;
        }


        Date date = null;
        String strDate;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(data.getText());
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            strDate = "" + year + '-' + month + '-' + day + "";
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "La data inserita non valida", "Error: data non valida", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        /* Inviare query e dare messaggio di conferma */


        if (tipo.getValue().equals("COSTI")) {
            if(bancaCassa.getValue().equals("BANCA")) {
                model.setCosti(parrocchia.getValue(), strDate, tipo2.getValue(), descrizione.getText(), val, 0);
                JOptionPane.showMessageDialog(null, "Costo inserito correttamente", "Inserimento Costo", JOptionPane.INFORMATION_MESSAGE);
            }else if(bancaCassa.getValue().equals("CASSA")){
                model.setCosti(parrocchia.getValue(), strDate, tipo2.getValue(), descrizione.getText(), 0, val);
                JOptionPane.showMessageDialog(null, "Costo inserito correttamente", "Inserimento Costo", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if(tipo.getValue().equals("RICAVI")) {
            if(bancaCassa.getValue().equals("BANCA")) {
                model.setRicavi(parrocchia.getValue(),strDate, tipo2.getValue(), descrizione.getText(), val,0);
                JOptionPane.showMessageDialog(null, "Ricavo inserito correttamente", "Inserimento Ricavo", JOptionPane.INFORMATION_MESSAGE);
            }else if(bancaCassa.getValue().equals("CASSA")){
                model.setRicavi(parrocchia.getValue(), strDate, tipo2.getValue(), descrizione.getText(), 0,val);
                JOptionPane.showMessageDialog(null, "Ricavo inserito correttamente", "Inserimento Ricavo", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        descrizione.clear();
        valore.clear();
        data.clear();
        if(tabellaVisualizzata.getValue().equals("TUTTO")) {
            createTableRicavi();
            createTableCosti();
            tabellaVisualizzata.getSelectionModel().select(parrocchia.getValue());
        }else if(tabellaVisualizzata.getValue().equals(parrocchia.getValue())) {
            createTableCostiPerParrocchia(tabellaVisualizzata.getValue());
            createTableRicaviPerParrocchia(tabellaVisualizzata.getValue());
        }else{
            createTableCostiPerParrocchia(parrocchia.getValue());
            createTableRicaviPerParrocchia(parrocchia.getValue());
            tabellaVisualizzata.getSelectionModel().select(parrocchia.getValue());
        }
    }

    @FXML
    void deleteElement(ActionEvent event) {
        if (tipo3.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Inserire COSTO o RICAVO in 'Scegli tipologia'", "Error: 'Scegli tipologia' è nullo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int val;
        try {
            val = Integer.parseInt(id.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Inserire un numero intero (es. 120) nel campo 'ID'", "Error: not a integer", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        /* Inviare query e dare messaggio di conferma*/

        if (tipo3.getValue().equals("COSTI")) {
            model.deleteCosti(val);
            JOptionPane.showMessageDialog(null, "Costo rimosso correttamente", "Rimozione Costo", JOptionPane.INFORMATION_MESSAGE);
        } else if(tipo3.getValue().equals("RICAVI")) {
            model.deletetRicavi(val);
            JOptionPane.showMessageDialog(null, "Ricavo rimosso correttamente", "Rimozione Ricavo", JOptionPane.INFORMATION_MESSAGE);
        }

        id.clear();
        if(tabellaVisualizzata.getValue().equals("TUTTO")) {
            createTableRicavi();
            createTableCosti();
        }else{
            createTableCostiPerParrocchia(tabellaVisualizzata.getValue());
            createTableRicaviPerParrocchia(tabellaVisualizzata.getValue());
        }
    }

    @FXML
    void chooseBoxElement(ActionEvent event) {
        String item = tipo.getValue();
        if (item.equals(items[0])) {
            tipo2.setItems(FXCollections.observableArrayList(costi));
        } else if (item.equals(items[1])) {
            tipo2.setItems(FXCollections.observableArrayList(ricavi));
        }
    }

    @FXML
    void initialize() {
        assert tipo != null : "fx:id=\"tipo\" was not injected: check your FXML file 'sample.fxml'.";
        assert descrizione != null : "fx:id=\"descrizione\" was not injected: check your FXML file 'sample.fxml'.";
        assert valore != null : "fx:id=\"valore\" was not injected: check your FXML file 'sample.fxml'.";
        assert data != null : "fx:id=\"data\" was not injected: check your FXML file 'sample.fxml'.";
        assert tabellaCosti != null : "fx:id=\"tabellaCosti\" was not injected: check your FXML file 'sample.fxml'.";
        assert tabellaRicavi != null : "fx:id=\"tabellaRicavi\" was not injected: check your FXML file 'sample.fxml'.";
        assert aggiungi != null : "fx:id=\"aggiungi\" was not injected: check your FXML file 'sample.fxml'.";
        assert tipo2 != null : "fx:id=\"tipo2\" was not injected: check your FXML file 'sample.fxml'.";
        assert tipo3 != null : "fx:id=\"tipo3\" was not injected: check your FXML file 'sample.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'sample.fxml'.";
        assert model != null;
        assert bancaCassa != null : "fx:id=\"bancaCassa\" was not injected: check your FXML file 'sample.fxml'.";

        tipo.setItems(FXCollections.observableArrayList(items));
        tipo3.setItems(FXCollections.observableArrayList(items));
        bancaCassa.setItems(FXCollections.observableArrayList(items2));

        List<Parrochie> l = model.getParrocchie();
        List<String> ls = new ArrayList<>();
        ls.add("TUTTO");
        for(Parrochie x : l){
            ls.add(x.getNome());
        }

        parrocchia.setItems(FXCollections.observableArrayList(ls));
        tabellaVisualizzata.setItems(FXCollections.observableArrayList(ls));
        tabellaVisualizzata.getSelectionModel().selectFirst();

        createTableCosti();
        createTableRicavi();
    }

    @FXML
    void newWindows(ActionEvent event) throws Exception{
        ControllerR controller;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("riepilogo.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        controller = loader.getController();
        controller.setModel(model);

        stage.getIcons().add(new Image("icon2.png"));
        stage.setTitle("Riepilogo");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void setModel(Model model) {
        this.model = model;

    }

    public void createTableCosti(){
        List<Costi> listCosti = model.getCosti();
        System.out.println(listCosti);
        List<CostiRef> l = convertFormatCosti(listCosti);

        if(flag2) {

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


            quar.prefWidthProperty().bind(tabellaCosti.widthProperty().multiply(0.2));
            quin.prefWidthProperty().bind(tabellaCosti.widthProperty().multiply(0.25));
            banca.prefWidthProperty().bind(tabellaCosti.widthProperty().multiply(0.1));
            cassa.prefWidthProperty().bind(tabellaCosti.widthProperty().multiply(0.1));

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

            tabellaCosti.getColumns().addAll(pri, sec, ter, quar, quin, sest);
            tabellaCosti.setItems(FXCollections.observableArrayList(l));
            flag2 = false;
        }else {
            tabellaCosti.refresh();
            tabellaCosti.setItems(FXCollections.observableArrayList(l));
        }
    }

    public void createTableRicavi(){
        List<Ricavi> listRicavi = model.getRicavi();
        System.out.println(listRicavi);
        List<RicaviRef> l = convertFormatRicavi(listRicavi);

        if(flag1) {

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

            quar.prefWidthProperty().bind(tabellaRicavi.widthProperty().multiply(0.2));
            quin.prefWidthProperty().bind(tabellaRicavi.widthProperty().multiply(0.24));
            banca.prefWidthProperty().bind(tabellaCosti.widthProperty().multiply(0.1));
            cassa.prefWidthProperty().bind(tabellaCosti.widthProperty().multiply(0.1));

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

            tabellaRicavi.getColumns().addAll(pri, sec, ter, quar, quin, sest);
            tabellaRicavi.setItems(FXCollections.observableArrayList(l));
            flag1 = false;
        }else {

            tabellaRicavi.refresh();
            tabellaRicavi.setItems(FXCollections.observableArrayList(l));
        }
    }

    public static List<CostiRef> convertFormatCosti(List<Costi> l){
        List<CostiRef> list = new ArrayList<>();
        String pattern = "€###,###.###";
        DecimalFormat formatter = new DecimalFormat(pattern);

        for(Costi x : l){
            String parrocchiaid = x.getParrocchiaid();
            int costoid = x.getCostoid();
            String nome = x.getNome();
            String descrizione = x.getDescrizione();
            String date = x.getDate();
            double valorebanca = x.getValoreBanca();
            double valorecassa = x.getValoreCassa();

            String valorebancastr;
            if(valorebanca == 0){
                valorebancastr = "";
            }else{
                valorebancastr = formatter.format(valorebanca);
            }

            String valorecassastr;
            if(valorecassa == 0){
                valorecassastr = "";
            }else{
                valorecassastr = formatter.format(valorecassa);
            }

            list.add(new CostiRef(parrocchiaid,costoid,date,nome,descrizione,valorebancastr,valorecassastr));
        }
        return list;
    }

    public static List<RicaviRef> convertFormatRicavi(List<Ricavi> l){
        List<RicaviRef> list = new ArrayList<>();
        String pattern = "€###,###.###";
        DecimalFormat formatter = new DecimalFormat(pattern);

        for(Ricavi x : l){
            String parrocchiaid = x.getParrocchiaid();
            int ricavoid = x.getRicavoid();
            String nome = x.getNome();
            String descrizione = x.getDescrizione();
            String date = x.getDate();
            double valorebanca = x.getValoreBanca();
            double valorecassa = x.getValoreCassa();

            String valorebancastr;
            if(valorebanca == 0){
                valorebancastr = "";
            }else{
                valorebancastr = formatter.format(valorebanca);
            }

            String valorecassastr;
            if(valorecassa == 0){
                valorecassastr = "";
            }else{
                valorecassastr = formatter.format(valorecassa);
            }

            list.add(new RicaviRef(parrocchiaid,ricavoid,date,nome,descrizione,valorebancastr,valorecassastr));
        }
        return list;
    }

    public void createTableCostiPerParrocchia(String parrocchia){
        List<Costi> listCosti = model.getCostiPerParrocchie(parrocchia);
        List<CostiRef> l = convertFormatCosti(listCosti);

        if(flag2) {

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


            quar.prefWidthProperty().bind(tabellaCosti.widthProperty().multiply(0.2));
            quin.prefWidthProperty().bind(tabellaCosti.widthProperty().multiply(0.25));
            banca.prefWidthProperty().bind(tabellaCosti.widthProperty().multiply(0.1));
            cassa.prefWidthProperty().bind(tabellaCosti.widthProperty().multiply(0.1));

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

            tabellaCosti.getColumns().addAll(pri, sec, ter, quar, quin, sest);
            tabellaCosti.setItems(FXCollections.observableArrayList(l));
            flag2 = false;
        }else {
            tabellaCosti.refresh();
            tabellaCosti.setItems(FXCollections.observableArrayList(l));
        }
    }

    public void createTableRicaviPerParrocchia(String parrochia){
        List<Ricavi> listRicavi = model.getRicaviPerParrocchie(parrochia);
        List<RicaviRef> l = convertFormatRicavi(listRicavi);

        if(flag1) {

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

            quar.prefWidthProperty().bind(tabellaRicavi.widthProperty().multiply(0.2));
            quin.prefWidthProperty().bind(tabellaRicavi.widthProperty().multiply(0.24));
            banca.prefWidthProperty().bind(tabellaCosti.widthProperty().multiply(0.1));
            cassa.prefWidthProperty().bind(tabellaCosti.widthProperty().multiply(0.1));

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

            tabellaRicavi.getColumns().addAll(pri, sec, ter, quar, quin, sest);
            tabellaRicavi.setItems(FXCollections.observableArrayList(l));
            flag1 = false;
        }else {

            tabellaRicavi.refresh();
            tabellaRicavi.setItems(FXCollections.observableArrayList(l));
        }
    }
}


