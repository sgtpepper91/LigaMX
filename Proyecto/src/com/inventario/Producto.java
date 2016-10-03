package com.inventario;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            setSql("UPDATE PRODUCTOS SET EXISTENCIAS = ? WHERE CLAVEPROD = ?");
            crearPreparedStatement();
            getPstmn().setInt(1, existencias - cantidad);
            getPstmn().setInt(2, claveProd);
            return ejecutarUpdate();
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }

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
            setSql("SELECT * FROM PRODUCTOS WHERE DESCRIPCIONPROD = ?");
            crearPreparedStatement();
            getPstmn().setString(1, descripcion);
            ejecutarQuery();
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
        try {
            setSql("INSERT INTO PRODUCTOS (DESCRIPCIONPROD,EXISTENCIAS,COSTOUNITARIO,PRECIOUNITARIO) VALUES (?,?,?,?)");
            setPstmn(getConn().prepareStatement(getSql()));
            getPstmn().setString(1, descripcionProd);
            getPstmn().setInt(2, existencias);
            getPstmn().setFloat(3, costoUnitario);
            getPstmn().setFloat(4, precioUnitario);
            return ejecutarUpdate();
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    /**
     * Actualiza un nuevo producto en la tabla
     *
     * @return true si fue exitoso, false en caso contrario
     * @throws Excepcion
     */
    boolean actualizar()  throws Excepcion{
        try {
            setSql("UPDATE PRODUCTOS SET DESCRIPCIONPROD = ?, EXISTENCIAS = ?, COSTOUNITARIO = ? ,PRECIOUNITARIO = ? WHERE CLAVEPROD = ?");
            crearPreparedStatement();
            getPstmn().setString(1, descripcionProd);
            getPstmn().setInt(2, existencias);
            getPstmn().setInt(3, costoUnitario);
            getPstmn().setInt(4, precioUnitario);
            getPstmn().setInt(5, claveProd);
            return ejecutarUpdate();
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }
}
