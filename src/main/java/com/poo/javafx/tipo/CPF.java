package com.poo.javafx.tipo;

public class CPF extends Tipo {

    public CPF(String valor) {
        super(formatar(valor));
    }

    private static String formatar(String valor) {
        String limpo = valor != null ? valor.replaceAll("[^\\d]", "") : "";
        if (limpo.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos.");
        }
        return limpo.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
}
