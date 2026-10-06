package com.poo.javafx.validacao;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;

public class RegraComboBox extends RegraValidacao {

    public RegraComboBox() {
        this("Selecione uma opção válida.");
    }

    public RegraComboBox(String mensagemErro) {
        super(mensagemErro);
    }

    @Override
    public boolean suporta(Node componente) {
        return componente instanceof ComboBox<?>;
    }

    @Override
    protected boolean valido(Node componente) {
        return ((ComboBox<?>) componente).getValue() != null;
    }

    @Override
    public void limpar(Node componente) {
        ((ComboBox<?>) componente).setValue(null);
    }
}
