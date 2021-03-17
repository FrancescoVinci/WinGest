package gestioneRiepilogo;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.*;
import pdfGeneretor.GeneratePdf;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerR {
    private Model model = new Model();

    public static String globRicavi;
    public static String globCosti;
    public static String globParrocchia;
    public boolean flag1 = true;
    public boolean flag2 = true;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button stampaPdfButton;

    @FXML
    private TableView<RiepilogoCostiRef> tableCosti;

    @FXML
    private TableView<RiepilogoRicaviRef> tableRicavi;

    @FXML
    private TextField totCostiCassa;

    @FXML
    private TextField totCostiBanca;

    @FXML
    private TextField totRicaviCassa;

    @FXML
    private TextField totRicaviBanca;

    @FXML
    private ComboBox<String> riepilogoVisualizzato;

    @FXML
    void changeTableRiepilogo(ActionEvent event) {
        createTableRicaviPerParrocchia(riepilogoVisualizzato.getValue());
        createTableCostiPerParrocchia(riepilogoVisualizzato.getValue());

        totCostiBanca.setStyle("-fx-text-fill: red; -fx-alignment: CENTER-RIGHT");
        totCostiCassa.setStyle("-fx-text-fill: red; -fx-alignment: CENTER-RIGHT");
        Pair<String,String> result = totaleCosti(riepilogoVisualizzato.getValue());
        totCostiBanca.setText(result.getKey());
        totCostiCassa.setText(result.getValue());

        totRicaviBanca.setStyle("-fx-text-fill: green; -fx-alignment: CENTER-RIGHT");
        totRicaviCassa.setStyle("-fx-text-fill: green; -fx-alignment: CENTER-RIGHT");
        Pair<String,String> result2 = totaleRicavi(riepilogoVisualizzato.getValue());
        totRicaviBanca.setText(result2.getKey());
        totRicaviCassa.setText(result2.getValue());
    }

    @FXML
    void printPdf(ActionEvent event) {
        List<RiepilogoCosti> listCosti = model.getRiepilogoCostiPerParrocchie(riepilogoVisualizzato.getValue());
        List<RiepilogoCostiRef> l = convertFormatCosti(listCosti);

        List<RiepilogoRicavi> listRicavi = model.getRiepilogoRicaviPerParrocchie(riepilogoVisualizzato.getValue());
        List<RiepilogoRicaviRef> l2 = convertFormatRicavi(listRicavi);

        GeneratePdf pdf = new GeneratePdf(l,l2);
        pdf.createPdf();
        System.out.println("Prova");
    }

    @FXML
    void initialize() {
        assert totCostiCassa != null : "fx:id=\"totCostiCassa\" was not injected: check your FXML file 'riepilogo.fxml'.";
        assert stampaPdfButton != null : "fx:id=\"stampaPdfButton\" was not injected: check your FXML file 'riepilogo.fxml'.";
        assert totRicaviCassa != null : "fx:id=\"totRicaviCassa\" was not injected: check your FXML file 'riepilogo.fxml'.";
        assert totCostiBanca != null : "fx:id=\"totCostiBanca\" was not injected: check your FXML file 'riepilogo.fxml'.";
        assert tableCosti != null : "fx:id=\"tableCosti\" was not injected: check your FXML file 'riepilogo.fxml'.";
        assert tableRicavi != null : "fx:id=\"tableRicavi\" was not injected: check your FXML file 'riepilogo.fxml'.";
        assert totRicaviBanca != null : "fx:id=\"totRicaviBanca\" was not injected: check your FXML file 'riepilogo.fxml'.";


        List<Parrochie> l = model.getParrocchie();
        List<String> ls = new ArrayList<>();
        for(Parrochie x : l){
            ls.add(x.getNome());
        }

        riepilogoVisualizzato.setItems(FXCollections.observableArrayList(ls));
        riepilogoVisualizzato.getSelectionModel().selectFirst();

        createTableCostiPerParrocchia(riepilogoVisualizzato.getValue());
        createTableRicaviPerParrocchia(riepilogoVisualizzato.getValue());

        totCostiBanca.setStyle("-fx-text-fill: red; -fx-alignment: CENTER-RIGHT");
        totCostiCassa.setStyle("-fx-text-fill: red; -fx-alignment: CENTER-RIGHT");
        Pair<String,String> result = totaleCosti(riepilogoVisualizzato.getValue());
        totCostiBanca.setText(result.getKey());
        totCostiCassa.setText(result.getValue());

        totRicaviBanca.setStyle("-fx-text-fill: green; -fx-alignment: CENTER-RIGHT");
        totRicaviCassa.setStyle("-fx-text-fill: green; -fx-alignment: CENTER-RIGHT");
        Pair<String,String> result2 = totaleRicavi(riepilogoVisualizzato.getValue());
        totRicaviBanca.setText(result2.getKey());
        totRicaviCassa.setText(result2.getValue());


    }


    public void setModel(Model model) {
        this.model = model;
    }

    public void createTableCostiPerParrocchia(String parrocchiaid){
        List<RiepilogoCosti> listCosti = model.getRiepilogoCostiPerParrocchie(parrocchiaid);
        List<RiepilogoCostiRef> l = convertFormatCosti(listCosti);

        if(flag1) {

            TableColumn<RiepilogoCostiRef, String> pri = new TableColumn<>("ParrocchiaID");
            TableColumn<RiepilogoCostiRef, String> sec = new TableColumn<>("Nome");
            TableColumn<RiepilogoCostiRef, String> ter = new TableColumn<>("Valore");

            TableColumn<RiepilogoCostiRef, String> banca = new TableColumn<>("Banca");
            TableColumn<RiepilogoCostiRef, String> cassa = new TableColumn<>("Cassa");

            PropertyValueFactory<RiepilogoCostiRef, String> primoColFactory = new PropertyValueFactory<>("parrocchiaid");
            PropertyValueFactory<RiepilogoCostiRef, String> secondoColFactory = new PropertyValueFactory<>("nome");
            PropertyValueFactory<RiepilogoCostiRef, String> terzoColFactory = new PropertyValueFactory<>("valorebanca");
            PropertyValueFactory<RiepilogoCostiRef, String> quartoColFactory = new PropertyValueFactory<>("valorecassa");


            sec.prefWidthProperty().bind(tableCosti.widthProperty().multiply(0.5));
            banca.prefWidthProperty().bind(tableCosti.widthProperty().multiply(0.1));
            cassa.prefWidthProperty().bind(tableCosti.widthProperty().multiply(0.1));

            ter.getColumns().addAll(banca, cassa);
            banca.setCellValueFactory(terzoColFactory);
            cassa.setCellValueFactory(quartoColFactory);

            pri.setCellValueFactory(primoColFactory);
            sec.setCellValueFactory(secondoColFactory);


            banca.setStyle("-fx-text-fill: red; -fx-alignment: CENTER-RIGHT");
            cassa.setStyle("-fx-text-fill: red; -fx-alignment: CENTER-RIGHT");


            tableCosti.getColumns().addAll(pri, sec, ter);
            tableCosti.setItems(FXCollections.observableArrayList(l));

            tableCosti.setRowFactory(tv -> {
                TableRow<RiepilogoCostiRef> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        try {

                            String data = row.getItem().getNome();
                            String data2 = row.getItem().getParrocchiaid();
                            globCosti = data;
                            globParrocchia = data2;

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("informationPanel2.fxml"));
                            Parent root = null;

                            root = loader.load();

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();

                            stage.getIcons().add(new Image("icon2.png"));
                            stage.setTitle("Riepilogo");
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return row;
            });
            flag1 = false;
        }else{
            tableCosti.refresh();
            tableCosti.setItems(FXCollections.observableArrayList(l));
            tableCosti.setRowFactory(tv -> {
                TableRow<RiepilogoCostiRef> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        try {

                            String data = row.getItem().getNome();
                            String data2 = row.getItem().getParrocchiaid();
                            globCosti = data;
                            globParrocchia = data2;

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("informationPanel2.fxml"));
                            Parent root = null;

                            root = loader.load();

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();

                            stage.getIcons().add(new Image("icon2.png"));
                            stage.setTitle("Riepilogo");
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return row;
            });
        }
    }

    public void createTableRicaviPerParrocchia(String parrocchiaid){
        List<RiepilogoRicavi> listRicavi = model.getRiepilogoRicaviPerParrocchie(parrocchiaid);
        List<RiepilogoRicaviRef> l = convertFormatRicavi(listRicavi);

        if(flag2) {

            TableColumn<RiepilogoRicaviRef, String> pri = new TableColumn<>("ParrocchiaID");
            TableColumn<RiepilogoRicaviRef, String> sec = new TableColumn<>("Nome");
            TableColumn<RiepilogoRicaviRef, String> ter = new TableColumn<>("Valore");

            TableColumn<RiepilogoRicaviRef, String> banca = new TableColumn<>("Banca");
            TableColumn<RiepilogoRicaviRef, String> cassa = new TableColumn<>("Cassa");


            PropertyValueFactory<RiepilogoRicaviRef, String> primoColFactory = new PropertyValueFactory<>("parrocchiaid");
            PropertyValueFactory<RiepilogoRicaviRef, String> secondoColFactory = new PropertyValueFactory<>("nome");
            PropertyValueFactory<RiepilogoRicaviRef, String> terzoColFactory = new PropertyValueFactory<>("valorebanca");
            PropertyValueFactory<RiepilogoRicaviRef, String> quartoColFactory = new PropertyValueFactory<>("valorecassa");


            sec.prefWidthProperty().bind(tableRicavi.widthProperty().multiply(0.5));
            banca.prefWidthProperty().bind(tableRicavi.widthProperty().multiply(0.1));
            cassa.prefWidthProperty().bind(tableRicavi.widthProperty().multiply(0.1));


            ter.getColumns().addAll(banca, cassa);
            banca.setCellValueFactory(terzoColFactory);
            cassa.setCellValueFactory(quartoColFactory);

            pri.setCellValueFactory(primoColFactory);
            sec.setCellValueFactory(secondoColFactory);

            banca.setStyle("-fx-text-fill: green; -fx-alignment: CENTER-RIGHT");
            cassa.setStyle("-fx-text-fill: green; -fx-alignment: CENTER-RIGHT");


            tableRicavi.getColumns().addAll(pri, sec, ter);
            tableRicavi.setItems(FXCollections.observableArrayList(l));

            tableRicavi.setRowFactory(tv -> {
                TableRow<RiepilogoRicaviRef> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        try {

                            String data = row.getItem().getNome();
                            String data2 = row.getItem().getParrocchiaid();
                            globRicavi = data;
                            globParrocchia = data2;

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("informationPanel.fxml"));
                            Parent root = null;

                            root = loader.load();

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();

                            stage.getIcons().add(new Image("icon2.png"));
                            stage.setTitle("Riepilogo");
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return row;
            });
            flag2 = false;
        }else{
            tableRicavi.refresh();
            tableRicavi.setItems(FXCollections.observableArrayList(l));

            tableRicavi.setRowFactory(tv -> {
                TableRow<RiepilogoRicaviRef> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        try {

                            String data = row.getItem().getNome();
                            String data2 = row.getItem().getParrocchiaid();
                            globRicavi = data;
                            globParrocchia = data2;

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("informationPanel.fxml"));
                            Parent root = null;

                            root = loader.load();

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();

                            stage.getIcons().add(new Image("icon2.png"));
                            stage.setTitle("Riepilogo");
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return row;
            });
        }
    }

    public Pair<String,String> totaleCosti(String parrocchiaid){
        List<RiepilogoCosti> listCosti = model.getRiepilogoCostiPerParrocchie(parrocchiaid);

        double totBanca = 0;
        double totCassa = 0;
        for(RiepilogoCosti x : listCosti){
            totBanca += x.getValoreBanca();
            totCassa += x.getValoreCassa();
        }

        String pattern = "€###,###.###";
        DecimalFormat formatter = new DecimalFormat(pattern);
        String strBanca = formatter.format(totBanca);
        String strCassa = formatter.format(totCassa);
        return new Pair<>(strBanca,strCassa);
    }

    public Pair<String,String> totaleRicavi(String parrocchiaid){
        List<RiepilogoRicavi> listRicavi = model.getRiepilogoRicaviPerParrocchie(parrocchiaid);

        double totBanca = 0;
        double totCassa = 0;
        for(RiepilogoRicavi x : listRicavi){
            totBanca += x.getValoreBanca();
            totCassa += x.getValoreCassa();
        }

        String pattern = "€###,###.###";
        DecimalFormat formatter = new DecimalFormat(pattern);
        String strBanca = formatter.format(totBanca);
        String strCassa = formatter.format(totCassa);
        return new Pair<>(strBanca,strCassa);

    }

    public List<RiepilogoCostiRef> convertFormatCosti(List<RiepilogoCosti> l){
        List<RiepilogoCostiRef> list = new ArrayList<>();
        String pattern = "€###,###.###";
        DecimalFormat formatter = new DecimalFormat(pattern);

        for(RiepilogoCosti x : l){
            String parrocchiaid = x.getParrocchiaid();
            String nome = x.getNome();
            double valbanca = x.getValoreBanca();
            double valcassa = x.getValoreCassa();

            String valorebancastr;
            if(valbanca == 0){
                valorebancastr = "";
            }else{
                valorebancastr = formatter.format(valbanca);
            }

            String valorecassastr;
            if(valcassa == 0){
                valorecassastr = "";
            }else{
                valorecassastr = formatter.format(valcassa);
            }

            list.add(new RiepilogoCostiRef(parrocchiaid, nome, valorebancastr, valorecassastr));
        }
        return list;
    }

    public List<RiepilogoRicaviRef> convertFormatRicavi(List<RiepilogoRicavi> l){
        List<RiepilogoRicaviRef> list = new ArrayList<>();
        String pattern = "€###,###.###";
        DecimalFormat formatter = new DecimalFormat(pattern);

        for(RiepilogoRicavi x : l){
            String parrocchiaid = x.getParrocchiaid();
            String nome = x.getNome();
            double valbanca = x.getValoreBanca();
            double valcassa = x.getValoreCassa();

            String valorebancastr;
            if(valbanca == 0){
                valorebancastr = "";
            }else{
                valorebancastr = formatter.format(valbanca);
            }

            String valorecassastr;
            if(valcassa == 0){
                valorecassastr = "";
            }else{
                valorecassastr = formatter.format(valcassa);
            }

            list.add(new RiepilogoRicaviRef(parrocchiaid, nome, valorebancastr, valorecassastr));
        }
        return list;
    }
}


