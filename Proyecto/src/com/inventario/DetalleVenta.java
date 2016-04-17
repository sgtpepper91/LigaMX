package com.inventario;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hecto
 */
public class DetalleVenta extends ClaseConexion{

    private int numVenta;
    private Producto producto;
    private int detVentaCantidad;
    private int detVentaPrecio;
    private int detVentaSubtotal;

    public DetalleVenta(int numVenta, Producto producto, int detVentaCantidad) {
        this.numVenta = numVenta;
        this.producto = producto;
        this.detVentaCantidad = detVentaCantidad;
        this.detVentaPrecio = producto.getPrecioUnitario();
        this.detVentaSubtotal = detVentaPrecio * detVentaCantidad;
    }

    public void InsertarDetalleVenta() {
        try {
            conexion.conectarBase();
            conexion.setSql("INSERT INTO DETALLEVENTAS (NUMVENTA,CLAVEPROD,DETVENTACANTIDAD,DETVENTAPRECIO,DETVENTASUBTOTAL) VALUES (?,?,?,?,?)");
            conexion.setPstmn(conexion.getConn().prepareStatement(conexion.getSql()));
            conexion.getPstmn().setInt(1, numVenta);
            conexion.getPstmn().setInt(2, producto.getClaveProd());
            conexion.getPstmn().setInt(3, detVentaCantidad);
            conexion.getPstmn().setFloat(4, detVentaPrecio);
            conexion.getPstmn().setFloat(5, detVentaSubtotal);
            conexion.getPstmn().executeUpdate();
            producto.ActualizarExistencias(detVentaPrecio);
            conexion.getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(DetalleVenta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getNumVenta() {
        return numVenta;
    }

    public void setNumVenta(int numVenta) {
        this.numVenta = numVenta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getDetVentaCantidad() {
        return detVentaCantidad;
    }

    public void setDetVentaCantidad(int detVentaCantidad) {
        this.detVentaCantidad = detVentaCantidad;
    }

    public int getDetVentaPrecio() {
        return detVentaPrecio;
    }

    public void setDetVentaPrecio(int detVentaPrecio) {
        this.detVentaPrecio = detVentaPrecio;
    }

    public int getDetVentaSubtotal() {
        return detVentaSubtotal;
    }

    public void setDetVentaSubtotal(int detVentaSubtotal) {
        this.detVentaSubtotal = detVentaSubtotal;
    }
}
