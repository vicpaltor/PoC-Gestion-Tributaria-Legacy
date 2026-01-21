package com.gestiontributaria;

public class NString {
    private String contenido;

    public NString() {
        this.contenido = "";
    }

    public NString(String texto) {
        this.contenido = (texto == null) ? "" : texto;
    }

    public void setValue(String nuevoValor) {
        this.contenido = (nuevoValor == null) ? "" : nuevoValor;
    }

    @Override
    public String toString() {
        return this.contenido;
    }
}
