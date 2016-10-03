package com.inventario;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author hecto
 */
public class ServiceInventario extends ConexionBD {

    private final Inventario inventario;
    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
    JComboBox jcProductos = new JComboBox();

    public ServiceInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    /**
     * Llena la tabla de clientes
     *
     * @throws com.inventario.Excepcion
     */
    public void llenarClientes() throws Excepcion {
        try {
            DefaultTableModel dm = (DefaultTableModel) inventario.getTbClientes().getModel();
            int rowCount = dm.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
            setSql("SELECT NOMBRECLIENTE, ACUMULADOCLIENTE FROM CLIENTES ORDER BY NOMBRECLIENTE");
            crearPreparedStatement();
            ejecutarQuery();
            while (getRset().next()) {
                String nombre = getRset().getString(1);
                float acumulado = getRset().getFloat(2);
                List row = new ArrayList();
                row.add(nombre);
                row.add(acumulado);
                dm.addRow(row.toArray());
            }
            cerrarConexion();
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    /**
     * Llena la tabla de historial de cliente
     *
     * @throws com.inventario.Excepcion
     */
    public void llenarHistorial() throws Excepcion {
        int numCliente = 0;
        String nomCliente = inventario.getTxtClienteVentas().getText();
        float Acumulado = 0.0F;
        try {
            setSql("SELECT NUMCLIENTE,ACUMULADOCLIENTE FROM CLIENTES WHERE NOMBRECLIENTE = ?");
            crearPreparedStatement();
            getPstmn().setString(1, nomCliente);
            ejecutarQuery();
            while (getRset().next()) {
                numCliente = getRset().getInt(1);
                Acumulado = getRset().getFloat(2);
            }
            DefaultTableModel dm = (DefaultTableModel) inventario.getTbHistorial().getModel();
            int rowCount = dm.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
            setSql("SELECT D.DETVENTACANTIDAD AS CANTIDAD , P.DESCRIPCIONPROD AS PRODUCTO, D.DETVENTASUBTOTAL AS TOTAL, V.FECHAVENTA AS FECHA "
                    + "FROM CLIENTES C INNER JOIN VENTAS V ON C.NUMCLIENTE=V.NUMCLIENTE "
                    + "INNER JOIN DETALLEVENTAS D ON V.NUMVENTA=D.NUMVENTA "
                    + "INNER JOIN PRODUCTOS P ON D.CLAVEPROD=P.CLAVEPROD "
                    + "WHERE C.NUMCLIENTE = ? "
                    + "UNION ALL SELECT NULL AS CANTIDAD, 'Pago' AS TOTAL, A.PAGO AS TOTAL, A.FECHA "
                    + "FROM PAGOS A INNER JOIN CLIENTES C ON C.NUMCLIENTE=A.NUMCLIENTE "
                    + "WHERE C.NUMCLIENTE = ? ORDER BY FECHA DESC");
            crearPreparedStatement();
            getPstmn().setInt(1, numCliente);
            getPstmn().setInt(2, numCliente);
            ejecutarQuery();
            while (getRset().next()) {
                int cantidad = getRset().getInt(1);
                String descripcion = getRset().getString(2);
                float total = getRset().getFloat(3);
                java.util.Date fecha = getRset().getDate(4);

                List row = new ArrayList();
                row.add(cantidad);
                row.add(descripcion);
                row.add(total);
                row.add(this.df.format(fecha));
                dm.addRow(row.toArray());
            }
            inventario.getTxtTotalCliente().setValue(Acumulado);
            cerrarConexion();
        } catch (SQLException e) {
            throw lanzarExcepcion(e);
        }
    }

    /**
     * Llena la tabla Inventario
     */
    void llenarInventario() throws Excepcion {
        try {
            setSql("SELECT SUM(ACUMULADOCLIENTE) FROM CLIENTES");
            crearPreparedStatement();
            ejecutarQuery();
            while (getRset().next()) {
                inventario.getTxtTotalDeudas().setValue(getRset().getFloat(1));
            }
            setSql("SELECT SUM(TOTALVENTA) FROM VENTAS WHERE FECHAVENTA BETWEEN '19-09-2016' AND SYSDATE");
            crearPreparedStatement();
            ejecutarQuery();
            while (getRset().next()) {
                inventario.getTxtTotalVentas().setValue(getRset().getFloat(1));
            }
            DefaultTableModel dm = (DefaultTableModel) inventario.getTbInventario().getModel();
            int rowCount = dm.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
            setSql("SELECT DESCRIPCIONPROD, EXISTENCIAS, COSTOUNITARIO, PRECIOUNITARIO FROM PRODUCTOS WHERE DESCRIPCIONPROD != 'Ajuste' ORDER BY DESCRIPCIONPROD");
            crearPreparedStatement();
            ejecutarQuery();
            while (getRset().next()) {
                String Descripcion = getRset().getString(1);
                int Existencias = getRset().getInt(2);
                float Costo = getRset().getFloat(3);
                float Precio = getRset().getFloat(4);
                List row = new ArrayList();
                row.add(Descripcion);
                row.add(Existencias);
                row.add(Costo);
                row.add(Precio);
                dm.addRow(row.toArray());
            }
            cerrarConexion();
        } catch (SQLException e) {
            throw lanzarExcepcion(e);
        }
    }

    /**
     * Llena el comboBox de productos
     *
     * @throws com.inventario.Excepcion
     */
    public void llenarProductos() throws Excepcion {
        String producto;
        try {
            TableColumn col = inventario.getTbVenta().getColumnModel().getColumn(1);
            setSql("SELECT DESCRIPCIONPROD FROM PRODUCTOS WHERE DESCRIPCIONPROD != 'Ajuste' ORDER BY DESCRIPCIONPROD ");
            crearPreparedStatement();
            ejecutarQuery();
            comboModel.removeAllElements();
            while (getRset().next()) {
                producto = getRset().getString("DESCRIPCIONPROD");
                comboModel.addElement(producto);
            }
            jcProductos.setModel(comboModel);
            getConn().close();
            col.setCellEditor(new DefaultCellEditor(jcProductos));
        } catch (SQLException e) {
            throw lanzarExcepcion(e);
        }
    }

    /**
     * Realiza la devolución del producto
     *
     * @param devolucion
     */
    boolean hacerDevolucion(Devolucion devolucion) throws Excepcion {
        int opcion = JOptionPane.showConfirmDialog(inventario, "¿Desea devolver el producto:" + devolucion.getProducto() + "?", "Devolución", JOptionPane.YES_NO_OPTION);
        boolean actualizarVenta = false;
        boolean eliminarVenta = false;
        boolean devolverProducto = false;
        boolean borrarDetalle = false;
        boolean actualizarAcumulado = false;
        if (opcion == JOptionPane.YES_OPTION) {
            if (devolucion.getCantidad() > 1) {
                devolverProducto = devolucion.devolverProducto();
            } else {
                borrarDetalle = devolucion.borrarDetalle();
            }
            if (devolverProducto || borrarDetalle) {
                Venta venta = new Venta();
                venta.obtenerVenta(devolucion.getNumVenta());
                if (venta.getTotalVenta() > devolucion.getPrecioProd()) {
                    actualizarVenta = venta.actualizarVenta(devolucion.getPrecioProd());
                } else {
                    eliminarVenta = venta.eliminarVenta();
                }
                if (actualizarVenta || eliminarVenta) {
                    Producto producto = new Producto().RecuperarProducto(devolucion.getProducto());
                    if (producto.ActualizarExistencias(-1)) {
                        Cliente cliente = new Cliente();
                        cliente.RecuperarCliente(devolucion.getNombreCliente());
                        actualizarAcumulado = cliente.ActualizarAcumulado(-devolucion.getPrecioProd());
                    }
                }
            }
        }
        return actualizarAcumulado;
    }
}
