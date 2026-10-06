package com.poo.javafx.validacao;

import javafx.scene.Node;

public abstract class RegraValidacao {
    protected String mensagemErro;

    public RegraValidacao(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public abstract boolean suporta(Node componente);

    protected abstract boolean valido(Node componente);

    public abstract void limpar(Node componente);

    public ResultadoValidacao executar(Node componente) {
        if (!valido(componente)) {
            return ResultadoValidacao.falha(this.mensagemErro);
        }

        return ResultadoValidacao.sucesso();
    }
}
