package com.inventario;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
            Map<Integer, Object> params = new HashMap<>();
            Map<Integer, Object> params2 = new HashMap<>();
            setSql("INSERT INTO VENTAS (FECHAVENTA,NUMCLIENTE,TOTALVENTA) VALUES (?,?,?)");
            params.put(1, java.sql.Date.valueOf(fechaAMD));
            params.put(2, numCliente);
            params.put(3, totalVenta);
            if (ejecutarUpdate(params)) {
                setSql("SELECT NUMVENTA FROM VENTAS WHERE FECHAVENTA = TO_DATE(?, 'dd-mm-yyyy') AND NUMCLIENTE = ? ORDER BY NUMVENTA DESC");
                params2.put(1, formatoDiaMesAño.format(fechaVenta));
                params2.put(2, numCliente);
                ejecutarQuery(params2);
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
            Map<Integer, Object> params = new HashMap<>();
            setSql("SELECT TOTALVENTA FROM VENTAS WHERE NUMVENTA = ?");
            params.put(1, numVenta);
            ejecutarQuery(params);
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
        Map<Integer, Object> params = new HashMap<>();
        setSql("UPDATE VENTAS SET TOTALVENTA = ? WHERE NUMVENTA = ?");
        params.put(1, total);
        params.put(2, numVenta);
        return ejecutarUpdate(params);
    }

    /**
     * Elimina la venta
     *
     * @return true si fue exitoso, false en caso contrario
     * @throws com.inventario.Excepcion
     */
    public boolean eliminarVenta() throws Excepcion {
        Map<Integer, Object> params = new HashMap<>();
        setSql("DELETE VENTAS WHERE NUMVENTA = ?");
        params.put(1, numVenta);
        return ejecutarUpdate(params);
    }
}
