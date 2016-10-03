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
public class Venta extends ConexionBD {

    private int numVenta;
    private Date fechaVenta;
    private int numCliente;
    private int totalVenta;
    private final DateFormat formatoAñoMesDia = new SimpleDateFormat("yyyy-MM-dd");
    private final DateFormat formatoDiaMesAño = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Constructor para clase Venta
     *
     * @param cliente
     * @param fechaVenta
     * @param totalVenta
     */
    public Venta(Cliente cliente, Date fechaVenta, int totalVenta) {
        this.fechaVenta = fechaVenta;
        this.numCliente = cliente.getNumCliente();
        this.totalVenta = totalVenta;
    }

    /**
     * Constructor default para clase Venta
     */
    Venta() {

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

    /**
     * Insertar venta en tabla VENTAS
     *
     * @return true si fue exitoso, false en caso contrario
     * @throws com.inventario.Excepcion
     */
    public boolean insertarVenta() throws Excepcion {
        try {
            String fechaAMD = formatoAñoMesDia.format(fechaVenta);
            setSql("INSERT INTO VENTAS (FECHAVENTA,NUMCLIENTE,TOTALVENTA) VALUES (?,?,?)");
            crearPreparedStatement();
            getPstmn().setDate(1, java.sql.Date.valueOf(fechaAMD));
            getPstmn().setInt(2, numCliente);
            getPstmn().setFloat(3, totalVenta);
            int res = getPstmn().executeUpdate();
            if (ejecutarUpdate()) {
                setSql("SELECT NUMVENTA FROM VENTAS WHERE FECHAVENTA = ? AND NUMCLIENTE = ? ORDER BY NUMVENTA DESC");
                crearPreparedStatement();
                getPstmn().setString(1, formatoDiaMesAño.format(fechaVenta));
                getPstmn().setInt(2, numCliente);
                ejecutarQuery();
                if (getRset().next()) {
                    numVenta = getRset().getInt(1);
                }
                cerrarConexion();
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    /**
     * Obtiene el total de la venta
     *
     * @param numVenta
     * @throws com.inventario.Excepcion
     */
    public void obtenerVenta(int numVenta) throws Excepcion {
        try {
            setSql("SELECT TOTALVENTA FROM VENTAS WHERE NUMVENTA = ?");
            crearPreparedStatement();
            getPstmn().setInt(1, numVenta);
            ejecutarQuery();
            while (getRset().next()) {
                this.numVenta = numVenta;
                this.totalVenta = getRset().getInt(1);
            }
            cerrarConexion();

        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    /**
     * Actualiza el total de la venta
     *
     * @param total
     * @return true si fue exitoso, false en caso contrario
     * @throws com.inventario.Excepcion
     */
    public boolean actualizarVenta(int total) throws Excepcion {
        totalVenta -= total;
        try {
            setSql("UPDATE VENTAS SET TOTALVENTA = ? WHERE NUMVENTA = ?");
            crearPreparedStatement();
            getPstmn().setInt(1, total);
            getPstmn().setInt(2, numVenta);
            return ejecutarUpdate();
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    /**
     * Elimina la venta
     *
     * @return true si fue exitoso, false en caso contrario
     * @throws com.inventario.Excepcion
     */
    public boolean eliminarVenta() throws Excepcion {
        try {
            setSql("DELETE VENTAS WHERE NUMVENTA = ?");
            crearPreparedStatement();
            getPstmn().setInt(1, numVenta);
            return ejecutarUpdate();
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }
}
