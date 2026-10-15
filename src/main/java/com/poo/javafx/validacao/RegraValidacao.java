package com.poo.javafx.validacao;

import javafx.scene.Node;

/**
 * Padrão Strategy: cada subclasse é um algoritmo de validação. O CRUDController escolhe a
 * primeira regra cujo suporta() é verdadeiro e dá break; regras adicionadas com
 * adicionarRegra entram no início da lista e têm precedência sobre as padrão.
 */
public abstract class RegraValidacao {
    protected String mensagemErro;

    public RegraValidacao(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public abstract boolean suporta(Node componente);

    protected abstract boolean valido(Node componente);

    public abstract void limpar(Node componente);

    /** Template Method sobre valido(): executa a validação e monta o ResultadoValidacao. */
    public ResultadoValidacao executar(Node componente) {
        if (!valido(componente)) {
            return ResultadoValidacao.falha(this.mensagemErro);
        }

        return ResultadoValidacao.sucesso();
    }
}
