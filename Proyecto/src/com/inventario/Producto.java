package com.inventario;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hecto
 */
public class Producto extends ConexionBD {

    private int claveProd;
    private String descripcionProd;
    private int existencias;
    private BigDecimal costoUnitario;
    private BigDecimal precioUnitario;
    private int categoria;

    public Producto(String descripcionProd, int existencias, BigDecimal costoUnitario, BigDecimal precioUnitario, int categoria) {
        this.descripcionProd = descripcionProd;
        this.existencias = existencias;
        this.costoUnitario = costoUnitario;
        this.precioUnitario = precioUnitario;
        this.categoria = categoria;
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

    public BigDecimal getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(BigDecimal costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    
    /**
     * Actualiza la tabla de Existencias
     *
     * @param cantidad
     * @return true si fue exitoso, false en caso contrario
     * @throws com.inventario.Excepcion
     */
    public boolean ActualizarExistencias(int cantidad) throws Excepcion {
        Map<Integer, Object> params = new HashMap<>();
        setSql("UPDATE PRODUCTOS SET EXISTENCIAS = ? WHERE CLAVEPROD = ?");
        params.put(1, existencias - cantidad);
        params.put(2, claveProd);
        return ejecutarUpdate(params);

    }

    /**
     * Recupera los datos de un producto
     *
     * @param descripcion
     * @return El producto
     * @throws com.inventario.Excepcion
     */
    public Producto RecuperarProducto(String descripcion) throws Excepcion {
        try {
            Map<Integer, Object> params = new HashMap<>();
            setSql("SELECT * FROM PRODUCTOS WHERE DESCRIPCIONPROD = ?");
            params.put(1, descripcion);
            ejecutarQuery(params);
            while (getRset().next()) {
                claveProd = getRset().getInt(1);
                existencias = getRset().getInt(3);
                costoUnitario = getRset().getBigDecimal(4).setScale(2);
                precioUnitario = getRset().getBigDecimal(5).setScale(2);
                descripcionProd = descripcion;
                categoria = getRset().getInt(6);
            }
            cerrarConexion();
            return this;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    /**
     * Inserta un nuevo producto en la tabla
     *
     * @return true si fue exitoso, false en caso contrario
     * @throws Excepcion
     */
    boolean InsertarProducto() throws Excepcion {
        Map<Integer, Object> params = new HashMap<>();
        setSql("INSERT INTO PRODUCTOS (DESCRIPCIONPROD,EXISTENCIAS,COSTOUNITARIO,PRECIOUNITARIO, CATEGORIA) VALUES (?,?,?,?,?)");
        params.put(1, descripcionProd);
        params.put(2, existencias);
        params.put(3, costoUnitario);
        params.put(4, precioUnitario);
        params.put(5, categoria);
        return ejecutarUpdate(params);
    }

    /**
     * Actualiza un nuevo producto en la tabla
     *
     * @return true si fue exitoso, false en caso contrario
     * @throws Excepcion
     */
    boolean actualizar()  throws Excepcion{
        Map<Integer, Object> params = new HashMap<>();
        setSql("UPDATE PRODUCTOS SET DESCRIPCIONPROD = ?, EXISTENCIAS = ?, COSTOUNITARIO = ?, PRECIOUNITARIO = ?, CATEGORIA = ? WHERE CLAVEPROD = ?");
        params.put(1, descripcionProd);
        params.put(2, existencias);
        params.put(3, costoUnitario);
        params.put(4, precioUnitario);
        params.put(5, categoria);
        params.put(6, claveProd);
        return ejecutarUpdate(params);
    }
}
