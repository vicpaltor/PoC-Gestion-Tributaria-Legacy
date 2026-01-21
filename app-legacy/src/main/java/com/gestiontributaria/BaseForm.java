package com.gestiontributaria;

public abstract class BaseForm {

    public void ejecutarCicloDeVida() {
        System.out.println("\n--- [FRAMEWORK MORPHIS SIMULADO] Inicio ---");
        try {
            preQuery();       // 1. Preparar
            executeQuery();   // 2. Ejecutar PL/SQL
            postQuery();      // 3. Mostrar resultados
        } catch (Exception e) {
            System.err.println("ERROR CRÍTICO: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("--- [FRAMEWORK MORPHIS SIMULADO] Fin ---\n");
    }

    protected abstract void preQuery();
    protected abstract void executeQuery() throws Exception;
    protected abstract void postQuery();
}
