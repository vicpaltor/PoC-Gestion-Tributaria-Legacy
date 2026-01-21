package com.gestiontributaria;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FormularioValoracion extends BaseForm {

    private int idInmueble;
    private NString zona;
    private NString valor;

    public FormularioValoracion(int id) {
        this.idInmueble = id;
        this.zona = new NString();
        this.valor = new NString();
    }

    @Override
    protected void preQuery() {
        System.out.println("[JAVA: Pre-Query] Validando ID: " + idInmueble);
        if (idInmueble <= 0) throw new RuntimeException("ID Inválido");
    }

    @Override
    protected void executeQuery() throws Exception {
        System.out.println("[JAVA: Execute-Query] Llamando a PL/SQL 'PROCESO_COMPLETO_VALORACION'...");

        try (Connection conn = ConexionBD.getConexion()) {
            String sql = "{call PROCESO_COMPLETO_VALORACION(?)}";

            try (CallableStatement stmt = conn.prepareCall(sql)) {
                stmt.setInt(1, this.idInmueble);
                stmt.execute(); // Esto Calcula Y Notifica en BD

                System.out.println(">> BD respondió OK. Recuperando datos actualizados...");
                recuperarDatos(conn);
            }
        }
    }

    private void recuperarDatos(Connection conn) throws Exception {
        String query = "SELECT ZONA, VALOR_CATASTRAL FROM INMUEBLES WHERE ID = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, this.idInmueble);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    this.zona.setValue(rs.getString("ZONA"));
                    this.valor.setValue(String.valueOf(rs.getDouble("VALOR_CATASTRAL")));
                }
            }
        }
    }

    @Override
    protected void postQuery() {
        System.out.println("[JAVA: Post-Query] Resultado:");
        System.out.println("   -> Zona: " + zona);
        System.out.println("   -> Valoración: " + valor + " €");

        if ("CENTRO".equals(zona.toString())) {
            System.out.println("   [INFO] Inmueble en zona prime.");
        }
    }
}