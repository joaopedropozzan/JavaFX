package com.poo.javafx.validacao;

import javafx.scene.Node;
import javafx.scene.control.DatePicker;

public class RegraDatePicker extends RegraValidacao {
    public RegraDatePicker() {
        this("Selecione uma data válida.");
    }

    public RegraDatePicker(String mensagemErro) {
        super(mensagemErro);
    }

    @Override
    public boolean suporta(Node componente) {
        return componente instanceof DatePicker;
    }

    @Override
    protected boolean valido(Node componente) {
        return ((DatePicker) componente).getValue() != null;
    }

    @Override
    public void limpar(Node componente) {
        ((DatePicker) componente).setValue(null);
    }
}
