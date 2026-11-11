package com.poo.javafx.carteira;

import com.poo.javafx.Model;
import com.poo.javafx.passageiro.PassageiroModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carteiras")
public class CarteiraModel extends Model<CarteiraModel> {
    @Column(nullable = false)
    private double saldo;

    @Column(nullable = false, unique = true)
    private String numeroCartao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "titular_id", nullable = false)
    private PassageiroModel titular;

    protected CarteiraModel() {
    }

    public CarteiraModel(double saldo, String numeroCartao, PassageiroModel titular) {
        this.saldo = saldo;
        this.numeroCartao = numeroCartao;
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public PassageiroModel getTitular() {
        return titular;
    }
}
