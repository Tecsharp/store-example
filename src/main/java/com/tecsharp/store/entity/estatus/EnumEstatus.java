package com.tecsharp.store.entity.estatus;

public enum EnumEstatus { 
    
	ENABLED(1), 
    DISABLED(0);

    private int estatus;

    EnumEstatus(int estatus) {
        this.estatus = estatus;
    }

    public int getEstatus() { 
        return estatus;
    }
}
