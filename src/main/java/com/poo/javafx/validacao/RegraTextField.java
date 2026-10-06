package com.poo.javafx.validacao;

import javafx.scene.Node;
import javafx.scene.control.TextField;

public class RegraTextField extends RegraValidacao {
    public RegraTextField() {
        this("O campo de texto não pode ficar vazio.");
    }

    public RegraTextField(String mensagemErro) {
        super(mensagemErro);
    }

    @Override
    public boolean suporta(Node componente) {
        return componente instanceof TextField;
    }

    @Override
    protected boolean valido(Node componente) {
        TextField tf = (TextField) componente;
        return tf.getText() != null && !tf.getText().isBlank();
    }

    @Override
    public void limpar(Node componente) {
        ((TextField) componente).clear();
    }
}
