package com.inventario;

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

    /**
     * Actualiza la tabla de Existencias
     *
     * @param cantidad
     * @return true si fue exitoso, false en caso contrario
     * @throws com.inventario.Excepcion
     */
    public boolean ActualizarExistencias(int cantidad) throws Excepcion {
        Map params = new HashMap();
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
            Map params = new HashMap();
            setSql("SELECT * FROM PRODUCTOS WHERE DESCRIPCIONPROD = ?");
            params.put(1, descripcion);
            ejecutarQuery(params);
            while (getRset().next()) {
                claveProd = getRset().getInt(1);
                existencias = getRset().getInt(3);
                costoUnitario = getRset().getInt(4);
                precioUnitario = getRset().getInt(5);
                descripcionProd = descripcion;
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
        Map params = new HashMap();
        setSql("INSERT INTO PRODUCTOS (DESCRIPCIONPROD,EXISTENCIAS,COSTOUNITARIO,PRECIOUNITARIO) VALUES (?,?,?,?)");
        params.put(1, descripcionProd);
        params.put(2, existencias);
        params.put(3, costoUnitario);
        params.put(4, precioUnitario);
        return ejecutarUpdate(params);
    }

    /**
     * Actualiza un nuevo producto en la tabla
     *
     * @return true si fue exitoso, false en caso contrario
     * @throws Excepcion
     */
    boolean actualizar()  throws Excepcion{
        Map params = new HashMap();
        setSql("UPDATE PRODUCTOS SET DESCRIPCIONPROD = ?, EXISTENCIAS = ?, COSTOUNITARIO = ? ,PRECIOUNITARIO = ? WHERE CLAVEPROD = ?");
        params.put(1, descripcionProd);
        params.put(2, existencias);
        params.put(3, costoUnitario);
        params.put(4, precioUnitario);
        params.put(5, claveProd);
        return ejecutarUpdate(params);
    }
}
