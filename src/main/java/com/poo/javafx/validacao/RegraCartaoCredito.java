package com.poo.javafx.validacao;

import javafx.scene.Node;
import javafx.scene.control.TextField;

public class RegraCartaoCredito extends RegraTextField {
    private final Node alvo;

    public RegraCartaoCredito(Node alvo) {
        super("O número do cartão deve conter exatamente 16 dígitos numéricos.");
        this.alvo = alvo;
    }

    @Override
    public boolean suporta(Node componente) {
        return componente == alvo;
    }

    @Override
    protected boolean valido(Node componente) {
        String limpo = ((TextField) componente).getText().replaceAll("\\s+", "");
        return limpo.matches("\\d{16}");
    }
}
