package com.poo.javafx.veiculo;

import java.util.Objects;

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

    @Override
    public String toString() {
        return this.placa + " - " + this.modelo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        VeiculoModel that = (VeiculoModel) o;
        if (this.getID() != 0 && that.getID() != 0) {
            return this.getID() == that.getID();
        }
        return Objects.equals(this.placa, that.placa);
    }

    @Override
    public int hashCode() {
        return getID() != 0 ? Objects.hash(getID()) : Objects.hash(placa);
    }

}
