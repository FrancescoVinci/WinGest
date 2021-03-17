package model;

import java.util.Objects;

public class CostiRef {

    private int costoid;
    private String nome,descrizione,date,valorebanca,valorecassa,parrocchiaid;

    public CostiRef(String parrocchiaid,int costoid, String date, String nome, String descrizione, String valorebanca, String valorecassa) {
        this.parrocchiaid = parrocchiaid;
        this.costoid = costoid;
        this.date = date;
        this.nome = nome;
        this.descrizione = descrizione;
        this.valorebanca = valorebanca;
        this.valorecassa = valorecassa;
    }

    public String getParrocchiaid() {
        return parrocchiaid;
    }

    public int getCostoid() {
        return costoid;
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

    public void setCostoid(int costoid) {
        this.costoid = costoid;
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
