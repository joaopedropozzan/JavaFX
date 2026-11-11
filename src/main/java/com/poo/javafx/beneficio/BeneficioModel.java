package com.poo.javafx.beneficio;

import com.poo.javafx.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "beneficios")
public class BeneficioModel extends Model<BeneficioModel> {
    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private String descricao;

    protected BeneficioModel() {
    }

    public BeneficioModel(String nome, String tipo, double valor, String descricao) {
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
