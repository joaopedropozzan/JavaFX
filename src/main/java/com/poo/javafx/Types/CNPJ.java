package com.poo.javafx.Types;

import java.io.Serializable;

public class CNPJ implements Serializable {
    private final String valor;

    public CNPJ(String valor) {
        String limpo = valor.replaceAll("[^\\d]", "");
        if (limpo.length() != 14) {
            throw new IllegalArgumentException("CNPJ deve conter 14 dígitos.");
        }
        this.valor = limpo.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
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
        CNPJ cnpj = (CNPJ) o;
        return valor.equals(cnpj.valor);
    }
}
