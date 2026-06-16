package com.poo.javafx.Instanciacao.JoaoPozzan;

import java.io.Serial;
import com.poo.javafx.Model;

public class CarteiraModel extends Model<CarteiraModel> {
    @Serial
    private static final long serialVersionUID = 1L;

    private int saldo;
    private int numeroCartao;
    private String titularConta;

    public CarteiraModel(int saldo, int numeroCartao, String titularConta) {
        this.saldo = saldo;
        this.numeroCartao = numeroCartao;
        this.titularConta = titularConta;
    }

    @Override
    protected boolean checarColisao(CarteiraModel objeto) {
        // A regra de colisão valida se o número do cartão já existe no sistema
        return this.numeroCartao == objeto.getNumeroCartao();
    }

    public int getSaldo() {
        return saldo;
    }

    public int getNumeroCartao() {
        return numeroCartao;
    }

    public String getTitularConta() {
        return titularConta;
    }
}