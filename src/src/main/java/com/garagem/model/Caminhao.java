package com.garagem.model;

public class Caminhao extends Veiculo {
    private double capacidadeCarga; // em toneladas

    public Caminhao() {}

    public Caminhao(String id, String marca, String modelo, int ano, double capacidadeCarga) {
        super(id, marca, modelo, ano);
        this.capacidadeCarga = capacidadeCarga;
    }

    public double getCapacidadeCarga() { return capacidadeCarga; }
    public void setCapacidadeCarga(double capacidadeCarga) {
        this.capacidadeCarga = capacidadeCarga;
    }
}
