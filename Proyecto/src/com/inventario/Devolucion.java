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

    public Devolucion(String nombreCliente, int cantidad, String producto, int numVenta, int claveProd, int total) {
        this.nombreCliente = nombreCliente;
        this.cantidad = cantidad;
        this.producto = producto;
        this.numVenta = numVenta;
        this.claveProd = claveProd;
        this.total = total;
        precioProd = total / cantidad;
    }

    public int getPrecioProd() {
        return precioProd;
    }

    public void setPrecioProd(int precioProd) {
        this.precioProd = precioProd;
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

/**
 * Realiza la devolución de un producto
 * @return true si fue exitoso, false en caso contrario
 * @throws Excepcion 
 */
    boolean devolverProducto() throws Excepcion {
        total -= precioProd;
        try {
            setSql("UPDATE DETALLEVENTAS SET DETVENTACANTIDAD = ?, DETVENTASUBTOTAL = ? WHERE NUMVENTA = ? AND CLAVEPROD = ?");
            crearPreparedStatement();
            getPstmn().setInt(1, cantidad - 1);
            getPstmn().setInt(2, total);
            getPstmn().setInt(3, numVenta);
            getPstmn().setInt(4, claveProd);
            return ejecutarUpdate();
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    /**
     * Borra el detalle de venta del producto vendido
     * @return true si fue exitoso, false en caso contrario
     * @throws Excepcion 
     */
    boolean borrarDetalle() throws Excepcion {
        try {
            setSql("DELETE DETALLEVENTAS WHERE NUMVENTA = ? AND CLAVEPROD = ?");
            crearPreparedStatement();
            getPstmn().setInt(1, numVenta);
            getPstmn().setInt(2, claveProd);
            return ejecutarUpdate();
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }
}
