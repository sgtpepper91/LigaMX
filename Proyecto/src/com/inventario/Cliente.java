package com.inventario;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hecto
 */
public class Cliente {

    private int numCliente;
    private String nombreCliente;
    private int acumuladoCliente;

    public Cliente(String nombreCliente, int acumuladoCliente) {
        this.nombreCliente = nombreCliente;
        this.acumuladoCliente = acumuladoCliente;
    }

    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getAcumuladoCliente() {
        return acumuladoCliente;
    }

    public void setAcumuladoCliente(int acumuladoCliente) {
        this.acumuladoCliente = acumuladoCliente;
    }
    
    public void ActualizarAcumulado(ConexionBD conexion, int total){
        try {
            conexion.conectarBase();
            conexion.setSql("UPDATE CLIENTES SET ACUMULADOCLIENTE= " + (acumuladoCliente + total) + " WHERE NUMCLIENTE=" + numCliente + "");
            conexion.getStmn().executeUpdate(conexion.getSql());
            conexion.getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
    }
}
