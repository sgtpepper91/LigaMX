package com.inventario;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hecto
 */
public class Venta extends ClaseConexion{

    private int numVenta;
    private Date fechaVenta;
    private int numCliente;
    private int totalVenta;
    private final DateFormat formatoAñoMesDia = new SimpleDateFormat("yyyy-MM-dd");

    //private String str1Fecha = this.df1.format(fechaVenta);
    public Venta(Cliente cliente, Date fechaVenta, int totalVenta) {
        this.fechaVenta = fechaVenta;
        this.numCliente = numCliente;
        this.totalVenta = totalVenta;
    }

    public int getNumVenta() {
        return numVenta;
    }

    public void setNumVenta(int numVenta) {
        this.numVenta = numVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    public int getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(int totalVenta) {
        this.totalVenta = totalVenta;
    }

    public void insertarVenta() {
        try {
            String fechaAMD = formatoAñoMesDia.format(fechaVenta);
            conexion.conectarBase();
            conexion.setSql("INSERT INTO VENTAS (FECHAVENTA,NUMCLIENTE,TOTALVENTA) VALUES (?,?,?)");
            conexion.setPstmn(conexion.getConn().prepareStatement(conexion.getSql()));
            conexion.getPstmn().setDate(1, java.sql.Date.valueOf(fechaAMD));
            conexion.getPstmn().setInt(2, numCliente);
            conexion.getPstmn().setFloat(3, totalVenta);
            conexion.getPstmn().executeUpdate();
            conexion.setRset(conexion.getStmn().executeQuery("SELECT NUMVENTA FROM VENTAS WHERE FECHAVENTA='" + fechaAMD + "' and NUMCLIENTE=" + numCliente + " ORDER BY NUMVENTA DESC"));
            while (conexion.getRset().next()) {
                numVenta = conexion.getRset().getInt(1);
            }
            conexion.getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
