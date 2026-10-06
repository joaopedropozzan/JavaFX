package com.poo.javafx.validacao;

public record ResultadoValidacao(boolean valido, String mensagemErro) {
    public static ResultadoValidacao sucesso() {
        return new ResultadoValidacao(true, null);
    }

    public static ResultadoValidacao falha(String mensagem) {
        return new ResultadoValidacao(false, mensagem);
    }
}
