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
     * @throws Exception
     */
    public boolean insertarVenta() throws Exception {
        try {
            String fechaAMD = formatoAñoMesDia.format(fechaVenta);
            conectarBase();
            setSql("INSERT INTO VENTAS (FECHAVENTA,NUMCLIENTE,TOTALVENTA) VALUES (?,?,?)");
            setPstmn(getConn().prepareStatement(getSql()));
            getPstmn().setDate(1, java.sql.Date.valueOf(fechaAMD));
            getPstmn().setInt(2, numCliente);
            getPstmn().setFloat(3, totalVenta);
            int res = getPstmn().executeUpdate();
            if (res > 0) {
                setRset(getStmn().executeQuery("SELECT NUMVENTA FROM VENTAS WHERE FECHAVENTA='" + formatoDiaMesAño.format(fechaVenta)
                        + "' and NUMCLIENTE=" + numCliente + " ORDER BY NUMVENTA DESC"));
                if (getRset().next()) {
                    numVenta = getRset().getInt(1);
                }
                getConn().close();
                return Boolean.TRUE;
            } else {
                getConn().close();
                return Boolean.FALSE;
            }

        } catch (SQLException ex) {
            getConn().close();
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error al insertar venta");
        }
    }

    /**
     * Obtiene el total de la venta
     *
     * @param numVenta
     */
    public void obtenerVenta(int numVenta) {
        try {
            conectarBase();
            setRset(getStmn().executeQuery("SELECT TOTALVENTA FROM VENTAS WHERE NUMVENTA = " + numVenta + ""));
            while (getRset().next()) {
                this.numVenta = numVenta;
                this.totalVenta = getRset().getInt(1);
            }
            getConn().close();

        } catch (SQLException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Actualiza el total de la venta
     *
     * @param total
     * @return true si fue exitoso, false en caso contrario
     * @throws Exception
     */
    public boolean actualizarVenta(int total) throws Exception {
        totalVenta -= total;
        try {
            conectarBase();
            setSql("UPDATE VENTAS SET TOTALVENTA=" + totalVenta + " WHERE NUMVENTA=" + numVenta + "");
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
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error al actualizar venta");
        }
    }

    /**
     * Elimina la venta
     *
     * @return true si fue exitoso, false en caso contrario
     * @throws Exception
     */
    public boolean eliminarVenta() throws Exception {
        try {
            conectarBase();
            setSql("DELETE VENTAS WHERE NUMVENTA=" + numVenta + "");
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
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error al borrar venta");
        }
    }
}
