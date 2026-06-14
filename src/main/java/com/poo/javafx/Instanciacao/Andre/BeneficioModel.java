package com.poo.javafx.Instanciacao.Andre;

import java.io.Serial;

import com.poo.javafx.Model;

public class BeneficioModel extends Model<BeneficioModel> {
    @Serial
    private static final long serialVersionUID = 1L;

    private String nome;
    private String tipo;
    private double valor;
    private String descricao;

    public BeneficioModel(String nome, String tipo, double valor, String descricao) {
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
    }

    @Override
    protected boolean checarColisao(BeneficioModel objeto) {
        return this.nome.equalsIgnoreCase(objeto.getNome());
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
