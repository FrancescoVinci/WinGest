package model;


import java.util.Date;
import java.util.Objects;

public class Costi {

    private int costoid;
    private String nome,descrizione,date,parrocchiaid;
    private double valorebanca,valorecassa;

    public Costi(String parrocchiaid, int costoid, String date, String nome, String descrizione, double valorebanca, double valorecassa) {
        this.parrocchiaid = parrocchiaid;
        this.costoid = costoid;
        this.date = date;
        this.nome = nome;
        this.descrizione = descrizione;
        this.valorebanca = valorebanca;
        this.valorecassa = valorecassa;
    }

    public void setParrocchiaid(String parrocchiaid) {
        this.parrocchiaid = parrocchiaid;
    }

    public void setCostoid(int costoid) {
        this.costoid = costoid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setValoreBanca(double valorebanca) {
        this.valorebanca = valorebanca;
    }

    public void setValoreCassa(double valorecassa) {
        this.valorecassa = valorecassa;
    }

    public String getParrocchiaid() {
        return parrocchiaid;
    }

    public int getCostoid() {
        return costoid;
    }

    public String getDate() {
        return date;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public double getValoreBanca() {
        return valorebanca;
    }

    public double getValoreCassa() {
        return valorecassa;
    }

}
