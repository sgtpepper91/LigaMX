package com.inventario;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hecto
 */
public class Producto {

    private int claveProd;
    private String descripcionProd;
    private int existencias;
    private int costoUnitario;
    private int precioUnitario;

    public Producto(String descripcionProd, int existencias, int costoUnitario, int precioUnitario) {
        this.descripcionProd = descripcionProd;
        this.existencias = existencias;
        this.costoUnitario = costoUnitario;
        this.precioUnitario = precioUnitario;
    }

    Producto() {
        
    }

    public int getClaveProd() {
        return claveProd;
    }

    public void setClaveProd(int claveProd) {
        this.claveProd = claveProd;
    }

    public String getDescripcionProd() {
        return descripcionProd;
    }

    public void setDescripcionProd(String descripcionProd) {
        this.descripcionProd = descripcionProd;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public int getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(int costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void ActualizarExistencias(ConexionBD conexion, int cantidad) {
        try {
            conexion.conectarBase();
            conexion.setSql("UPDATE PRODUCTOS SET EXISTENCIAS= " + (existencias - cantidad) + " WHERE CLAVEPROD=" + claveProd + "");
            conexion.getStmn().executeUpdate(conexion.getSql());
            conexion.getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void RecuperarProducto(String descripcion, ConexionBD conexion) {
        try {
            conexion.conectarBase();
            conexion.setRset(conexion.getStmn().executeQuery("SELECT * FROM PRODUCTOS WHERE DESCRIPCIONPROD='" + descripcion + "'"));
            while (conexion.getRset().next()) {
                claveProd = conexion.getRset().getInt(1);
                existencias = conexion.getRset().getInt(3);
                costoUnitario = conexion.getRset().getInt(4);
                precioUnitario = conexion.getRset().getInt(4);
            }
            conexion.getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
