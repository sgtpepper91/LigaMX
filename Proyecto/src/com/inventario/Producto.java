package com.inventario;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hecto
 */
public class Producto extends ConexionBD{

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

    public void ActualizarExistencias(int cantidad) {
        try {
            conectarBase();
            setSql("UPDATE PRODUCTOS SET EXISTENCIAS= " + (existencias - cantidad) + " WHERE CLAVEPROD=" + claveProd + "");
            getStmn().executeUpdate(getSql());
            getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Producto  RecuperarProducto(String descripcion) {
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

    void InsertarProducto() {
        try {
            conectarBase();
            setSql("INSERT INTO PRODUCTOS (DESCRIPCIONPROD,EXISTENCIAS,COSTOUNITARIO,PRECIOUNITARIO) VALUES (?,?,?,?)");
            setPstmn(getConn().prepareStatement(getSql()));
            getPstmn().setString(1, descripcionProd);
            getPstmn().setInt(2, existencias);
            getPstmn().setFloat(3, costoUnitario);
            getPstmn().setFloat(4, precioUnitario);
            getPstmn().executeUpdate();
            JOptionPane.showMessageDialog(null, "Producto insertado", null, 1);
            getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
