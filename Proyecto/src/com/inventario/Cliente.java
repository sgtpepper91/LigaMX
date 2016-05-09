package com.inventario;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hecto
 */
public class Cliente extends ConexionBD {

    private int numCliente;
    private String nombreCliente;
    private int acumuladoCliente;
    private final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    public Cliente(String nombreCliente, int acumuladoCliente) {
        this.nombreCliente = nombreCliente;
        this.acumuladoCliente = acumuladoCliente;
    }

    Cliente() {

    }

    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getAcumuladoCliente() {
        return acumuladoCliente;
    }

    public void setAcumuladoCliente(int acumuladoCliente) {
        this.acumuladoCliente = acumuladoCliente;
    }

    public void ActualizarAcumulado(int total) {
        try {
            conectarBase();
            setSql("UPDATE CLIENTES SET ACUMULADOCLIENTE= " + (acumuladoCliente + total) + " WHERE NUMCLIENTE=" + numCliente + "");
            getStmn().executeUpdate(getSql());
            getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void RecuperarCliente(String nombre) {
        try {
            conectarBase();
            setRset(getStmn().executeQuery("SELECT NUMCLIENTE,ACUMULADOCLIENTE FROM CLIENTES WHERE NOMBRECLIENTE='" + nombre + "'"));
            nombreCliente = nombre;
            while (getRset().next()) {
                numCliente = getRset().getInt(1);
                acumuladoCliente = getRset().getInt(2);
            }
            getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void RecuperarCliente(DefaultTableModel dm) {
        try {
            conectarBase();
            setRset(getStmn().executeQuery("SELECT NOMBRECLIENTE, ACUMULADOCLIENTE FROM CLIENTES ORDER BY NOMBRECLIENTE"));
            while (getRset().next()) {
                Vector row = new Vector();
                nombreCliente = getRset().getString(1);
                acumuladoCliente = getRset().getInt(2);
                row.add(nombreCliente);
                row.add(acumuladoCliente);
                dm.addRow(row);
            }
            getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultListModel getListaClientes() {
        DefaultListModel listModel = new DefaultListModel();
        try {
            conectarBase();
            setRset(getStmn().executeQuery("SELECT NOMBRECLIENTE FROM CLIENTES ORDER BY 1"));
            while (getRset().next()) {
                listModel.addElement(getRset().getString("NOMBRECLIENTE"));
            }
            getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listModel;
    }

    public void EditarCliente(String cliente, String cnuevo) {
        try {
            conectarBase();
            setSql("UPDATE CLIENTES SET NOMBRECLIENTE= '" + cnuevo + "' WHERE NOMBRECLIENTE='" + cliente + "'");
            getStmn().executeUpdate(getSql());
            getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ObtenerHistorial(DefaultTableModel dm) {
        try {
            conectarBase();
            setRset(getStmn().executeQuery(
                    "SELECT D.DETVENTACANTIDAD AS CANTIDAD , P.DESCRIPCIONPROD AS PRODUCTO, D.DETVENTASUBTOTAL AS TOTAL, V.FECHAVENTA AS FECHA "
                    + "FROM CLIENTES C INNER JOIN VENTAS V ON C.NUMCLIENTE=V.NUMCLIENTE "
                    + "INNER JOIN DETALLEVENTAS D ON V.NUMVENTA=D.NUMVENTA "
                    + "INNER JOIN PRODUCTOS P ON D.CLAVEPROD=P.CLAVEPROD "
                    + "WHERE C.NUMCLIENTE= " + numCliente + " UNION ALL "
                    + "SELECT NULL AS CANTIDAD, 'Pago' AS TOTAL, A.PAGO AS TOTAL, A.FECHA "
                    + "FROM PAGOS A INNER JOIN CLIENTES C "
                    + "ON C.NUMCLIENTE=A.NUMCLIENTE "
                    + "WHERE C.NUMCLIENTE=" + numCliente + " ORDER BY FECHA DESC"));
            while (getRset().next()) {
                int cantidad = getRset().getInt(1);
                String descripcion = getRset().getString(2);
                float total = getRset().getFloat(3);
                java.util.Date fecha = getRset().getDate(4);
                Vector row = new Vector();
                row.add(cantidad);
                row.add(descripcion);
                row.add(total);
                row.add(df.format(fecha));
                dm.addRow(row);
            }
            getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ObtenerHistorialDevolucion(DefaultTableModel dm) {
        try {
            conectarBase();
            setRset(getStmn().executeQuery(
                    "SELECT D.DETVENTACANTIDAD AS CANTIDAD , P.DESCRIPCIONPROD AS PRODUCTO, D.DETVENTASUBTOTAL AS TOTAL, V.FECHAVENTA AS FECHA, V.NUMVENTA, P.CLAVEPROD "
                    + "FROM CLIENTES C INNER JOIN VENTAS V ON C.NUMCLIENTE=V.NUMCLIENTE "
                    + "INNER JOIN DETALLEVENTAS D ON V.NUMVENTA=D.NUMVENTA "
                    + "INNER JOIN PRODUCTOS P ON D.CLAVEPROD=P.CLAVEPROD "
                    + "WHERE C.NUMCLIENTE= " + numCliente + " AND P.DESCRIPCIONPROD !='Ajuste' ORDER BY FECHA DESC"));
            while (getRset().next()) {
                int cantidad = getRset().getInt(1);
                String descripcion = getRset().getString(2);
                float total = getRset().getFloat(3);
                java.util.Date fecha = getRset().getDate(4);
                int numVenta = getRset().getInt(5);
                int claveProd = getRset().getInt(6);
                Vector row = new Vector();
                row.add(cantidad);
                row.add(descripcion);
                row.add(total);
                row.add(df.format(fecha));
                row.add(numVenta);
                row.add(claveProd);
                dm.addRow(row);
            }
            getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        void InsertarCliente() {
        try {
                conectarBase();
                setSql("INSERT INTO CLIENTES (NOMBRECLIENTE, ACUMULADOCLIENTE) VALUES (?,?)");
                setPstmn(getConn().prepareStatement(getSql()));
                getPstmn().setString(1, nombreCliente);
                getPstmn().setFloat(2, acumuladoCliente);
                getPstmn().executeUpdate();
                JOptionPane.showMessageDialog(null, "Cliente insertado", null, 1);
                getConn().close();
            } catch (SQLException ex) {
                Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
