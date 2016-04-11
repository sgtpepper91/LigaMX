package com.inventario;

import java.util.Date;

/**
 *
 * @author hecto
 */
public class Pago {
    private Cliente cliente;
    private int pago;
    private Date fecha;

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
}
