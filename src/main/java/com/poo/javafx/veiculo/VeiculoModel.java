package com.poo.javafx.veiculo;

import com.poo.javafx.Model;
import com.poo.javafx.tipo.Placa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "veiculos")
public class VeiculoModel extends Model<VeiculoModel> {
    @Column(nullable = false, unique = true)
    private Placa placa;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private int ano;

    protected VeiculoModel() {
    }

    public VeiculoModel(String placa, String modelo, int ano) {
        this.placa = new Placa(placa);
        this.modelo = modelo;
        this.ano = ano;
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
