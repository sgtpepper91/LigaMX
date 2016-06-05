package com.inventario;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hecto
 */
public class Pago extends ConexionBD {

    private Cliente cliente;
    private int pago;
    private Date fecha;
    private final DateFormat formatoAñoMesDia = new SimpleDateFormat("yyyy-MM-dd");

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Pago(Cliente cliente, int pago, Date fecha) {
        this.cliente = cliente;
        this.pago = pago;
        this.fecha = fecha;
    }

    /**
     * Inserta pago en la tabla Pagos
     * @return true si fue exitoso, false en caso contrario
     * @throws Exception 
     */
    boolean InsertarPago() throws Exception {
        try {
            String fechaAMD = formatoAñoMesDia.format(fecha);
            conectarBase();
            setSql("INSERT INTO PAGOS (NUMCLIENTE, PAGO, FECHA) VALUES (?,?,?)");
            setPstmn(getConn().prepareStatement(getSql()));
            getPstmn().setInt(1, cliente.getNumCliente());
            getPstmn().setFloat(2, pago);
            getPstmn().setDate(3, java.sql.Date.valueOf(fechaAMD));
            int res = getPstmn().executeUpdate();
            if (res > 0) {
                cliente.ActualizarAcumulado(-pago);
                getConn().close();
                return Boolean.TRUE;
            } else {
                getConn().close();
                return Boolean.FALSE;
            }
        } catch (SQLException ex) {
            getConn().close();
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error al insertar pago");
        }
    }
}
