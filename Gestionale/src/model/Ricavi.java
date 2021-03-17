package model;

import java.sql.Date;
import java.util.Objects;

public class Ricavi {

    private int ricavoid;
    private String nome,descrizione,date,parrocchiaid;
    private double valorebanca, valorecassa;

    public Ricavi(String parrocchiaid, int ricavoid, String date, String nome, String descrizione, double valorebanca, double valorecassa) {
        this.parrocchiaid = parrocchiaid;
        this.ricavoid = ricavoid;
        this.date = date;
        this.nome = nome;
        this.descrizione = descrizione;
        this.valorebanca = valorebanca;
        this.valorecassa = valorecassa;
    }

    public String getParrocchiaid() {
        return parrocchiaid;
    }

    public int getRicavoid() {
        return ricavoid;
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

    public void setParrocchiaid(String parrocchiaid) {
        this.parrocchiaid = parrocchiaid;
    }

    public void setRicavoid(int ricavoid) {
        this.ricavoid = ricavoid;
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

}
