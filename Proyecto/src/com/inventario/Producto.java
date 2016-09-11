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
     * @throws Exception
     */
    public boolean ActualizarExistencias(int cantidad) throws Exception {
        try {
            conectarBase();
            setSql("UPDATE PRODUCTOS SET EXISTENCIAS= " + (existencias - cantidad) + " WHERE CLAVEPROD=" + claveProd + "");
            int res = getStmn().executeUpdate(getSql());
            if (res > 0) {
                getConn().close();
                return Boolean.TRUE;
            } else {
                getConn().close();
                return Boolean.FALSE;
            }
        } catch (SQLException ex) {
            getConn().close();
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error al actualizar la tabla existencias");
        }

    }

    /**
     * Recupera los datos de un producto
     *
     * @param descripcion
     * @return El producto
     */
    public Producto RecuperarProducto(String descripcion) {
        try {
            conectarBase();
            setRset(getStmn().executeQuery("SELECT * FROM PRODUCTOS WHERE DESCRIPCIONPROD='" + descripcion + "'"));
            while (getRset().next()) {
                claveProd = getRset().getInt(1);
                existencias = getRset().getInt(3);
                costoUnitario = getRset().getInt(4);
                precioUnitario = getRset().getInt(5);
            }
            getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }

    /**
     * Inserta un nuevo producto en la tabla
     *
     * @return true si fue exitoso, false en caso contrario
     * @throws Exception
     */
    boolean InsertarProducto() throws Exception {
        try {
            conectarBase();
            setSql("INSERT INTO PRODUCTOS (DESCRIPCIONPROD,EXISTENCIAS,COSTOUNITARIO,PRECIOUNITARIO) VALUES (?,?,?,?)");
            setPstmn(getConn().prepareStatement(getSql()));
            getPstmn().setString(1, descripcionProd);
            getPstmn().setInt(2, existencias);
            getPstmn().setFloat(3, costoUnitario);
            getPstmn().setFloat(4, precioUnitario);
            int res = getPstmn().executeUpdate();
            if (res > 0) {
                getConn().close();
                return Boolean.TRUE;
            } else {
                getConn().close();
                return Boolean.FALSE;
            }
        } catch (SQLException ex) {
            getConn().close();
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error al insertar producto");
        }
    }

    /**
     * Actualiza un nuevo producto en la tabla
     *
     * @return true si fue exitoso, false en caso contrario
     * @throws Exception
     */
    boolean actualizar()  throws Exception{
        try {
            conectarBase();
            setSql("UPDATE PRODUCTOS SET DESCRIPCIONPROD = ?, EXISTENCIAS = ?, COSTOUNITARIO = ? ,PRECIOUNITARIO = ? WHERE CLAVEPROD = ?");
            setPstmn(getConn().prepareStatement(getSql()));
            getPstmn().setString(1, descripcionProd);
            getPstmn().setInt(2, existencias);
            getPstmn().setInt(3, costoUnitario);
            getPstmn().setInt(4, precioUnitario);
            getPstmn().setInt(5, claveProd);
            int res = getPstmn().executeUpdate();
            if (res > 0) {
                getConn().close();
                return Boolean.TRUE;
            } else {
                getConn().close();
                return Boolean.FALSE;
            }
        } catch (SQLException ex) {
            getConn().close();
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error al actualizar producto");
        }
    }
}
