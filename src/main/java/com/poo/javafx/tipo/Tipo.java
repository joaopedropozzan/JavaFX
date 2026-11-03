package com.poo.javafx.tipo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public abstract class Tipo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    protected final String valor;

    public Tipo(String valor) {
        this.valor = valor;
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
        Tipo outro = (Tipo) o;
        return Objects.equals(this.valor, outro.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), valor);
    }
}
