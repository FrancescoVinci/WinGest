package model;

public class Parrochie {

    private String parrocchiaid;

    public Parrochie(String parrocchiaid){
        this.parrocchiaid = parrocchiaid;
    }

    public String getNome() {
        return parrocchiaid;
    }

    public void setNome(String parrocchiaid) {
        this.parrocchiaid = parrocchiaid;
    }
}
