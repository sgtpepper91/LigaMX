package com.inventario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
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

    private Inventario inventario;
    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
    JComboBox jcProductos = new JComboBox();

    public ServiceInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    /**
     * Llena la tabla de clientes
     */
    public void llenarClientes() {
        try {
            conectarBase();
            DefaultTableModel dm = (DefaultTableModel) inventario.getTbClientes().getModel();
            int rowCount = dm.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
            setRset(getStmn().executeQuery("SELECT NOMBRECLIENTE, ACUMULADOCLIENTE FROM CLIENTES ORDER BY NOMBRECLIENTE"));
            while (getRset().next()) {
                String nombre = getRset().getString(1);
                float acumulado = getRset().getFloat(2);
                Vector row = new Vector();
                row.add(nombre);
                row.add(acumulado);
                dm.addRow(row);
            }
            getConn().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(inventario, e + "\nError en tablas");
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Llena la tabla de historial de cliente
     */
    public void llenarHistorial() {
        int NumCliente = 0;
        String Cliente = inventario.getTxtClienteVentas().getText();
        float Acumulado = 0.0F;
        try {
            conectarBase();
            setRset(getStmn().executeQuery("SELECT NUMCLIENTE,ACUMULADOCLIENTE FROM CLIENTES WHERE NOMBRECLIENTE='" + Cliente + "'"));
            while (getRset().next()) {
                NumCliente = getRset().getInt(1);
                Acumulado = getRset().getFloat(2);
            }
            DefaultTableModel dm = (DefaultTableModel) inventario.getTbHistorial().getModel();
            int rowCount = dm.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
            setRset(getStmn().executeQuery("SELECT D.DETVENTACANTIDAD AS CANTIDAD , P.DESCRIPCIONPROD AS PRODUCTO, D.DETVENTASUBTOTAL AS TOTAL, V.FECHAVENTA AS FECHA FROM CLIENTES C INNER JOIN VENTAS V ON C.NUMCLIENTE=V.NUMCLIENTE INNER JOIN DETALLEVENTAS D ON V.NUMVENTA=D.NUMVENTA INNER JOIN PRODUCTOS P ON D.CLAVEPROD=P.CLAVEPROD WHERE C.NUMCLIENTE=" + NumCliente + " " + "UNION ALL " + "SELECT NULL AS CANTIDAD, 'Pago' AS TOTAL, A.PAGO AS TOTAL, A.FECHA " + "FROM PAGOS A INNER JOIN CLIENTES C " + "ON C.NUMCLIENTE=A.NUMCLIENTE " + "WHERE C.NUMCLIENTE=" + NumCliente + " " + "ORDER BY FECHA DESC"));
            while (getRset().next()) {
                int cantidad = getRset().getInt(1);
                String descripcion = getRset().getString(2);
                float total = getRset().getFloat(3);
                java.util.Date fecha = getRset().getDate(4);

                Vector row = new Vector();
                row.add(cantidad);
                row.add(descripcion);
                row.add(total);
                row.add(this.df.format(fecha));
                dm.addRow(row);
            }
            inventario.getTxtTotalCliente().setValue(Acumulado);
            getConn().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(inventario, e + "\nError en tablas");
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Llena la tabla Inventario
     */
    void llenarInventario() {
        try {
            conectarBase();
            setRset(getStmn().executeQuery("SELECT SUM(ACUMULADOCLIENTE) FROM CLIENTES"));
            while (getRset().next()) {
                inventario.getTxtTotalDeudas().setValue(getRset().getFloat(1));
            }
            setRset(getStmn().executeQuery("SELECT SUM(TOTALVENTA) FROM VENTAS"));
            while (getRset().next()) {
                inventario.getTxtTotalVentas().setValue(getRset().getFloat(1));
            }
            DefaultTableModel dm = (DefaultTableModel) inventario.getTbInventario().getModel();
            int rowCount = dm.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
            setRset(getStmn().executeQuery("SELECT DESCRIPCIONPROD, EXISTENCIAS, COSTOUNITARIO, PRECIOUNITARIO FROM PRODUCTOS WHERE DESCRIPCIONPROD != 'Ajuste' ORDER BY DESCRIPCIONPROD"));
            while (getRset().next()) {
                String Descripcion = getRset().getString(1);
                int Existencias = getRset().getInt(2);
                float Costo = getRset().getFloat(3);
                float Precio = getRset().getFloat(4);
                Vector row = new Vector();
                row.add(Descripcion);
                row.add(Existencias);
                row.add(Costo);
                row.add(Precio);
                dm.addRow(row);
            }
            getConn().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(inventario, e + "\nError en tablas");
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Llena el comboBox de productos
     */
    public void llenarProductos() {
        String producto;
        try {
            conectarBase();    
            TableColumn col = inventario.getTbVenta().getColumnModel().getColumn(1);
            setRset(getStmn().executeQuery("SELECT DESCRIPCIONPROD FROM PRODUCTOS WHERE DESCRIPCIONPROD != 'Ajuste' ORDER BY DESCRIPCIONPROD "));
            comboModel.removeAllElements();
            while (getRset().next()) {
                producto = getRset().getString("DESCRIPCIONPROD");
                comboModel.addElement(producto);
            }
            jcProductos.setModel(comboModel);
            getConn().close();
            col.setCellEditor(new DefaultCellEditor(jcProductos));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(inventario, e + "\nError en tabla Productos");
        }
    }

    /**
     * Realiza la devolución del producto
     * @param devolucion 
     */
    boolean  hacerDevolucion(Devolucion devolucion) throws Exception{
        int opcion=JOptionPane.showConfirmDialog(inventario, "¿Desea devolver el producto:" + devolucion.getProducto() +"?","Devolución",JOptionPane.YES_NO_OPTION);
        if(opcion==JOptionPane.YES_OPTION)
        {
            if(devolucion.getCantidad()>1)
            {
                devolucion.devolverProducto();
            }
            else{
                devolucion.borrarDetalle();
            }
            Venta venta = new Venta();
            venta.obtenerVenta(devolucion.getNumVenta());
            if(venta.getTotalVenta()>devolucion.getPrecioProd())
            {
                venta.actualizarVenta(devolucion.getPrecioProd());
            }
            else{
                venta.eliminarVenta();
            }
            Producto producto = new Producto().RecuperarProducto(devolucion.getProducto());
            producto.ActualizarExistencias(-1);
            Cliente cliente = new Cliente();
            cliente.RecuperarCliente(devolucion.getNombreCliente());
            cliente.ActualizarAcumulado(-devolucion.getPrecioProd());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
