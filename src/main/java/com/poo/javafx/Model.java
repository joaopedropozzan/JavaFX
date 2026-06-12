package com.poo.javafx;

import java.io.Serializable;

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

    protected boolean checarColisao(T objeto) {
        return false;
    };
}
