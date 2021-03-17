package model;

public class RiepilogoCostiRef {

    private String parrocchiaid, nome, valorebanca, valorecassa;

    public RiepilogoCostiRef(String parrocchiaid, String nome, String valorebanca, String valorecassa){
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

    public String getValorebanca() {
        return valorebanca;
    }

    public String getValorecassa() {
        return valorecassa;
    }

    public void setParrocchiaid(String parrocchiaid) {
        this.parrocchiaid = parrocchiaid;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValorebanca(String valorebanca) {
        this.valorebanca = valorebanca;
    }

    public void setValorecassa(String valorecassa) {
        this.valorecassa = valorecassa;
    }
}
