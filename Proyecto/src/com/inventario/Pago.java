package com.inventario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
     Inserta pago en la tabla Pagos
     * @return true si fue exitoso, false en caso contrario
     * @throws Excepcion 
     */
    boolean InsertarPago() throws Excepcion {
        String fechaAMD = formatoAñoMesDia.format(fecha);
        Map<Integer, Object> params = new HashMap<>();
        setSql("INSERT INTO PAGOS (NUMCLIENTE, PAGO, FECHA) VALUES (?,?,?)");
        params.put(1, cliente.getNumCliente());
        params.put(2, pago);
        params.put(3, java.sql.Date.valueOf(fechaAMD));
        return ejecutarUpdate(params);
    }
}
