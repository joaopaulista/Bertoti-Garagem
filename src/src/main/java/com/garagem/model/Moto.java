package com.garagem.model;

public class Moto extends Veiculo {
    private int cilindradas;

    public Moto() {}

    public Moto(String id, String marca, String modelo, int ano, int cilindradas) {
        super(id, marca, modelo, ano);
        this.cilindradas = cilindradas;
    }

    public int getCilindradas() { return cilindradas; }
    public void setCilindradas(int cilindradas) {
        this.cilindradas = cilindradas;
    }
}
