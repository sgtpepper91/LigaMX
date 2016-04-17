package com.inventario;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hecto
 */
public class Cliente extends ClaseConexion {

    private int numCliente;
    private String nombreCliente;
    private int acumuladoCliente;

    public Cliente(String nombreCliente, int acumuladoCliente) {
        this.nombreCliente = nombreCliente;
        this.acumuladoCliente = acumuladoCliente;
    }

    Cliente() {
        
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

    public void ActualizarAcumulado(int total) {
        try {
            conexion.conectarBase();
            conexion.setSql("UPDATE CLIENTES SET ACUMULADOCLIENTE= " + (acumuladoCliente + total) + " WHERE NUMCLIENTE=" + numCliente + "");
            conexion.getStmn().executeUpdate(conexion.getSql());
            conexion.getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void RecuperarCliente(String nombre) {
        try {
            conexion.conectarBase();
            conexion.setRset(conexion.getStmn().executeQuery("SELECT NUMCLIENTE,ACUMULADOCLIENTE FROM CLIENTES WHERE NOMBRECLIENTE='" + nombre + "'"));
            nombreCliente = nombre;
            while (conexion.getRset().next()) {
                numCliente = conexion.getRset().getInt(1);
                acumuladoCliente = conexion.getRset().getInt(2);
            }
            conexion.getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
