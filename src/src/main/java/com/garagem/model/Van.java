package com.garagem.model;

public class Van extends Veiculo {
    private int capacidadePassageiros;

    public Van() {}

    public Van(String id, String marca, String modelo, int ano, int capacidadePassageiros) {
        super(id, marca, modelo, ano);
        this.capacidadePassageiros = capacidadePassageiros;
    }

    public int getCapacidadePassageiros() { return capacidadePassageiros; }
    public void setCapacidadePassageiros(int capacidadePassageiros) {
        this.capacidadePassageiros = capacidadePassageiros;
    }
}
