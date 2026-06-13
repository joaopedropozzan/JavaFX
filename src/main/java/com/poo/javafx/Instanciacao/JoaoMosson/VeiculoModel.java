package com.poo.javafx.Instanciacao.JoaoMosson;

import java.io.Serial;

import com.poo.javafx.Model;
import com.poo.javafx.Types.Placa;

public class VeiculoModel extends Model<VeiculoModel> {
    @Serial
    private static final long serialVersionUID = 1L;

    private Placa placa;
    private String modelo;
    private int ano;

    public VeiculoModel(String placa, String modelo, int ano) {
        this.placa = new Placa(placa);
        this.modelo = modelo;
        this.ano = ano;
    }

    protected boolean checarColisao(VeiculoModel objeto) {
        return this.placa.equals(objeto.getPlaca());
    }

    public Placa getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }
}
