package com.garagem.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Carro.class, name = "carro"),
        @JsonSubTypes.Type(value = Moto.class, name = "moto"),
        @JsonSubTypes.Type(value = Van.class, name = "van"),
        @JsonSubTypes.Type(value = Caminhao.class, name = "caminhao")
})
public abstract class Veiculo {

    private String id;
    private String marca;
    private String modelo;
    private int ano;

    public Veiculo() {}

    public Veiculo(String id, String marca, String modelo, int ano) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
}
