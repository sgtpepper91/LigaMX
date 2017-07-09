package com.inventario;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hecto
 */
public class DetalleVenta extends ConexionBD {

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

    /**
     * Inserta el detalle de venta en la tabla
     *
     * @return true si fue exitoso, false en caso contrario
     * @throws com.inventario.Excepcion
     */
    public boolean InsertarDetalleVenta() throws Excepcion {
        Map<Integer, Object> params = new HashMap<>();
        setSql("INSERT INTO DETALLEVENTAS (NUMVENTA,CLAVEPROD,DETVENTACANTIDAD,DETVENTAPRECIO,DETVENTASUBTOTAL) VALUES (?,?,?,?,?)");
        params.put(1, numVenta);
        params.put(2, producto.getClaveProd());
        params.put(3, detVentaCantidad);
        params.put(4, detVentaPrecio);
        params.put(5, detVentaSubtotal);
        if (ejecutarUpdate(params)){
            return producto.ActualizarExistencias(detVentaCantidad);
        }
        return false;
    }
}
