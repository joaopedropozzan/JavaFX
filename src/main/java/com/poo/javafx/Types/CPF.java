package com.poo.javafx.Types;

import java.io.Serializable;

public class CPF implements Serializable {
    private final String valor;

    public CPF(String valor) {
        String limpo = valor.replaceAll("[^\\d]", "");
        if (limpo.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos.");
        }
        this.valor = limpo.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CPF cpf = (CPF) o;
        return valor.equals(cpf.valor);
    }
}
