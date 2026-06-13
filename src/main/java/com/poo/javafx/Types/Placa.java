package com.poo.javafx.Types;

import java.io.Serializable;

public class Placa implements Serializable {
    private final String valor;

    public Placa(String valor) {
        String limpo = valor.toUpperCase().replaceAll("[^A-Z0-9]", "");
        if (!limpo.matches("[A-Z]{3}[0-9][A-Z0-9][0-9]{2}")) {
            throw new IllegalArgumentException("Placa formato inválido. Use ABC1234 ou ABC1D23.");
        }
        this.valor = limpo;
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
        Placa outra = (Placa) o;
        return valor.equals(outra.valor);
    }
}
