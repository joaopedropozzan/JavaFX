package com.poo.javafx.Instanciacao.JoaoMosson;

import java.io.Serial;

import com.poo.javafx.Model;

public class VeiculoModel extends Model {
    @Serial
    private static final long serialVersionUID = 1L;

    private String placa;
    private String modelo;
    private int ano;

    public VeiculoModel(String placa, String modelo, int ano) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
