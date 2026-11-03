package com.poo.javafx.tipo;

public class CNPJ extends Tipo {

    public CNPJ(String valor) {
        super(formatar(valor));
    }

    private static String formatar(String valor) {
        if (valor == null) {
            throw new IllegalArgumentException("CNPJ não pode ser nulo.");
        }
        String limpo = valor.replaceAll("[^\\d]", "");
        if (limpo.length() != 14) {
            throw new IllegalArgumentException("CNPJ deve conter 14 dígitos.");
        }
        return limpo.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }
}
