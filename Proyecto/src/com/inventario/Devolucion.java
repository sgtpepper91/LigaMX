package com.inventario;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Devolucion extends ConexionBD {

    private String nombreCliente;
    private int cantidad;
    private String producto;
    private int numVenta;
    private int claveProd;
    private int total;
    private int precioProd;

    public int getPrecioProd() {
        return precioProd;
    }

    public void setPrecioProd(int precioProd) {
        this.precioProd = precioProd;
    }

    public Devolucion(String nombreCliente, int cantidad, String producto, int numVenta, int claveProd, int total) {
        this.nombreCliente = nombreCliente;
        this.cantidad = cantidad;
        this.producto = producto;
        this.numVenta = numVenta;
        this.claveProd = claveProd;
        this.total = total;
        precioProd = total / cantidad;
    }

    public int getClaveProd() {
        return claveProd;
    }

    public void setClaveProd(int claveProd) {
        this.claveProd = claveProd;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getNumVenta() {
        return numVenta;
    }

    public void setNumVenta(int numVenta) {
        this.numVenta = numVenta;
    }

    void devolverProducto() {
        total -= precioProd;
        try {
            conectarBase();
            setSql("UPDATE DETALLEVENTAS SET DETVENTACANTIDAD= " + (cantidad-1) + ", DETVENTASUBTOTAL="+total+" WHERE NUMVENTA="+numVenta+" AND CLAVEPROD="+claveProd+"");
            getStmn().executeUpdate(getSql());
            getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void borrarDetalle() {
        try {
            conectarBase();
            setSql("DELETE DETALLEVENTAS WHERE NUMVENTA="+numVenta+" AND CLAVEPROD="+claveProd+"");
            getStmn().executeUpdate(getSql());
            getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

}