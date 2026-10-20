package com.poo.javafx.validacao;

import java.math.BigDecimal;

import javafx.scene.Node;
import javafx.scene.control.TextField;

public class RegraNumeroDecimal extends RegraTextField {
    private final Node alvo;
    private final Double valorMinimo;

    public RegraNumeroDecimal(Node alvo, String campoNome) {
        this(alvo, campoNome, null);
    }

    public RegraNumeroDecimal(Node alvo, String campoNome, Double valorMinimo) {
        super(valorMinimo == null
                ? campoNome + " deve ser um número decimal válido."
                : campoNome + " deve ser maior ou igual a " + valorMinimo + ".");
        this.alvo = alvo;
        this.valorMinimo = valorMinimo;
    }

    @Override
    public boolean suporta(Node componente) {
        return componente == alvo;
    }

    @Override
    protected boolean valido(Node componente) {
        try {
            double valor = new BigDecimal(((TextField) componente).getText().trim().replace(",", ".")).doubleValue();
            return !Double.isInfinite(valor) && (valorMinimo == null || valor >= valorMinimo);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
