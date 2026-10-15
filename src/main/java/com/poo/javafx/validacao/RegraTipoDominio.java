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
    public ResultadoValidacao executar(Node componente) {
        try {
            construtorTipo.accept(((TextField) componente).getText());
            return ResultadoValidacao.sucesso();
        } catch (IllegalArgumentException e) {
            return ResultadoValidacao.falha(e.getMessage());
        }
    }

}
