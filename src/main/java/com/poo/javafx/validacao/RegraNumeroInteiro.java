package com.poo.javafx.validacao;

import javafx.scene.Node;
import javafx.scene.control.TextField;

public class RegraNumeroInteiro extends RegraTextField {
    private final Node alvo;
    private final Integer valorMinimo;

    public RegraNumeroInteiro(Node alvo, String campoNome) {
        this(alvo, campoNome, null);
    }

    public RegraNumeroInteiro(Node alvo, String campoNome, Integer valorMinimo) {
        super(valorMinimo == null
                ? campoNome + " deve ser um número inteiro válido."
                : campoNome + " deve ser um número inteiro maior ou igual a " + valorMinimo + ".");
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
            int valor = Integer.parseInt(((TextField) componente).getText().trim());
            return valorMinimo == null || valor >= valorMinimo;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
