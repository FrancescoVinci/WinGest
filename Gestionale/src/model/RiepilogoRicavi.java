package model;

import java.util.Objects;

public class RiepilogoRicavi {

    private String parrocchiaid, nome;
    private double valorebanca, valorecassa;

    public RiepilogoRicavi(String parrocchiaid, String nome, double valorebanca, double valorecassa){
        this.parrocchiaid = parrocchiaid;
        this.nome = nome;
        this.valorebanca = valorebanca;
        this.valorecassa = valorecassa;
    }

    public String getParrocchiaid() {
        return parrocchiaid;
    }

    public String getNome() {
        return nome;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValoreBanca(double valorebanca) {
        this.valorebanca = valorebanca;
    }

    public void setValoreCassa(double valorecassa) {
        this.valorecassa = valorecassa;
    }
}
