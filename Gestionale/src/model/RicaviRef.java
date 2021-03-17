package model;

import java.util.Objects;

public class RicaviRef {

    private int ricavoid;
    private String nome, descrizione, date, valorebanca, valorecassa, parrocchiaid;

    public RicaviRef(String parrocchiaid, int ricavoid, String date, String nome, String descrizione, String valorebanca, String valorecassa) {
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

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getDate() {
        return date;
    }

    public String getValorebanca() {
        return valorebanca;
    }

    public String getValorecassa() {
        return valorecassa;
    }

    public void setParrocchiaid(String parrocchiaid) {
        this.parrocchiaid = parrocchiaid;
    }

    public void setRicavoid(int ricavoid) {
        this.ricavoid = ricavoid;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setValorebanca(String valorebanca) {
        this.valorebanca = valorebanca;
    }

    public void setValorecassa(String valorecassa) {
        this.valorecassa = valorecassa;
    }

}
