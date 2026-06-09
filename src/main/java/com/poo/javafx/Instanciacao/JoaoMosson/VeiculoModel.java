package com.poo.javafx.Instanciacao.JoaoMosson;

import java.io.Serial;
import java.io.Serializable;

public class VeiculoModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String placa;
    private String modelo;
    private int ano; // Campo numérico para cumprir a exigência do PDF

    public VeiculoModel(String placa, String modelo, int ano) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
    }

    // Getters e Setters
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
}