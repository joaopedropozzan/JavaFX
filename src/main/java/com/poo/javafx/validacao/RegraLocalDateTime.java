package com.poo.javafx.validacao;

import javafx.scene.Node;
import jfxtras.scene.control.LocalDateTimeTextField;

public class RegraLocalDateTime extends RegraValidacao {

    public RegraLocalDateTime() {
        this("Selecione a data e o horário.");
    }

    public RegraLocalDateTime(String mensagemErro) {
        super(mensagemErro);
    }

    @Override
    public boolean suporta(Node componente) {
        return componente instanceof LocalDateTimeTextField;
    }

    @Override
    protected boolean valido(Node componente) {
        return ((LocalDateTimeTextField) componente).getLocalDateTime() != null;
    }

    @Override
    public void limpar(Node componente) {
        ((LocalDateTimeTextField) componente).setLocalDateTime(null);
    }
}
