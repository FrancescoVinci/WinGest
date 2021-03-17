package model;

import db.CostiDAO;
import db.ParrochieDAO;
import db.RicaviDAO;
import javafx.util.Pair;
import java.util.List;

public class Model {

    private CostiDAO daoC;
    private RicaviDAO daoR;
    private ParrochieDAO daoP;

    public Model(){
        daoC = new CostiDAO();
        daoR = new RicaviDAO();
        daoP = new ParrochieDAO();
    }


    public void setCosti(String idparrocchia, String date, String nome, String descrizione, double valorebanca, double valorecassa){
        daoC.setCosti(idparrocchia, date,nome,descrizione,valorebanca, valorecassa);
    }

    public List<Costi> getCosti(){
        return daoC.getCosti();

    }

    public void setRicavi(String idparrocchia, String date, String nome, String descrizione, double valorebanca, double valorecassa){
        daoR.setRicavi(idparrocchia,date,nome,descrizione,valorebanca,valorecassa);
    }

    public List<Ricavi> getRicavi(){
        return daoR.getRicavi();

    }

    /*********************************************/

    public void deleteCosti(int id){
        daoC.deletetCosti(id);
    }

    public void deletetRicavi(int id){
        daoR.deletetRicavi(id);
    }

    public List<Ricavi> getRicaviPerParrocchie(String parrocchiaid){
        return daoR.getRicaviPerParrocchie(parrocchiaid);
    }

    public List<Costi> getCostiPerParrocchie(String parrocchiaid){
        return daoC.getCostiPerParrocchia(parrocchiaid);
    }

    /*********************************************/

    public List<RiepilogoCosti> getRiepilogoCostiPerParrocchie(String parrocchiaid){
        return daoC.getRiepilogoCostiPerParrocchie(parrocchiaid);
    }

    public List<RiepilogoRicavi> getRiepilogoRicaviPerParrocchie(String parrocchiaid){
        return daoR.getRiepilogoRicaviPerParrocchie(parrocchiaid);
    }

    /*********************************************/

    public List<Ricavi> getSelectedInformationRicavi(String parrocchiaid, String name){
        return daoR.getSelectedIformationRicavi(parrocchiaid, name);

    }

    public List<Costi> getSelectedInformationCosti(String parrocchiaid, String name){
        return daoC.getSelectedIformationCosti(parrocchiaid, name);

    }

    /*********************************************/

    public void addParrochie(String nome){
        daoP.addParrochie(nome);
    }

    public void deleteParrochie(String nome){
        daoP.deleteParrocchie(nome);
    }

    public List<Parrochie> getParrocchie(){
        return daoP.getParrochie();
    }

    /*********************************************/

    public void deleteCostiPerParrocchiaId(String id){
        daoC.deletetCostiPerParrocchiaId(id);
    }

    public void deleteRicaviPerParrocchiaId(String id){
        daoR.deletetRicaviPerParrocchiaId(id);
    }
}
