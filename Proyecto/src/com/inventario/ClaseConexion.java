package com.inventario;

/**
 *
 * @author hecto
 */
public class ClaseConexion {
    protected static ConexionBD conexion;

    public static ConexionBD getConexion() {
        return conexion;
    }

    public static void setConexion(ConexionBD conexion) {
        ClaseConexion.conexion = conexion;
    }
    
}
