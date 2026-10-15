package com.poo.javafx;

import java.io.Serializable;

/**
 * Padrão Template Method: colideCom é o método-template (final) e
 * checarColisao e herdarDe são ganchos que as subclasses podem sobrescrever.
 */
public abstract class Model<T extends Model<T>> implements Serializable {
    private int id;

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public final boolean colideCom(Object objeto) {
        if (this == objeto)
            return true;
        if (objeto == null || getClass() != objeto.getClass())
            return false;

        @SuppressWarnings("unchecked")
        T objetoFilho = (T) objeto;

        return checarColisao(objetoFilho);
    }

    /** Gancho: define quando dois objetos colidem (padrão: nunca). */
    protected boolean checarColisao(T objeto) {
        return false;
    };

    /** Gancho: copia de {@code original} o que deve sobreviver a uma atualização. */
    protected void herdarDe(T original) {
    }
}
