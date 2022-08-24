package com.tecsharp.store.entity.estatus;

public enum Estatus { 
    ENABLED(1), 
    DISABLED(0);

    private int estatus;

    Estatus(int estatus) {
        this.estatus = estatus;
    }

    public int getEstatus() { 
        return estatus;
    }
}
