package com.poo.javafx.carteira;

import com.poo.javafx.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "carteiras")
public class CarteiraModel extends Model<CarteiraModel> {
    @Column(nullable = false)
    private double saldo;

    @Column(nullable = false, unique = true)
    private String numeroCartao;

    @Column(nullable = false)
    private String titularConta;

    protected CarteiraModel() {
    }

    public CarteiraModel(double saldo, String numeroCartao, String titularConta) {
        this.saldo = saldo;
        this.numeroCartao = numeroCartao;
        this.titularConta = titularConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getTitularConta() {
        return titularConta;
    }
}
