package com.garagem.model;

public class Carro extends Veiculo {
    private String carroceria; // ex: Hatch, Sedan

    public Carro() {}

    public Carro(String id, String marca, String modelo, int ano, String carroceria ) {
        super(id, marca, modelo, ano);
        this.carroceria  = carroceria;
    }

    public String getCarroceria() { return carroceria; }
    public void setCarroceria(String carroceria) { this.carroceria = carroceria; }
}
