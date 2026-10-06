package com.poo.javafx.validacao;

import java.util.function.Consumer;

import javafx.scene.Node;
import javafx.scene.control.TextField;

public class RegraTipoDominio extends RegraTextField {
    private final Consumer<String> construtorTipo;
    private final Node alvo;

    public RegraTipoDominio(Node alvo, Consumer<String> construtorTipo) {
        super(null);
        this.alvo = alvo;

        this.construtorTipo = construtorTipo;
    }

    @Override
    public boolean suporta(Node componente) {
        return componente == alvo;
    }

    @Override
    protected boolean valido(Node componente) {
        String texto = ((TextField) componente).getText();
        try {
            construtorTipo.accept(texto);
            return true;
        } catch (IllegalArgumentException e) {
            this.mensagemErro = e.getMessage();
            return false;
        }
    }

}
