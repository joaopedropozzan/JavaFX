package com.poo.javafx;

import java.io.Serializable;

public abstract class Model implements Serializable {
    protected int id;

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
}
