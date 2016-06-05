package com.inventario;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
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

    /**
     * Actualiza el acumulado del cliente
     * @param total
     * @return true si fue exitoso, false en caso contrario
     * @throws Exception 
     */
    public boolean ActualizarAcumulado(int total) throws Exception {
        try {
            conectarBase();
            setSql("UPDATE CLIENTES SET ACUMULADOCLIENTE= " + (acumuladoCliente + total) + " WHERE NUMCLIENTE=" + numCliente + "");
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
            throw new Exception("Error al actualizar acumulado");

        }

    }

    /**
     * Recupera los datos del cliente
     * @param nombre
     * @throws Exception 
     */
    public void RecuperarCliente(String nombre) throws Exception {
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

    /**
     * Llena la tabla de Clientes
     * @param dm 
     */
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

    /**
     * Obtiene la lista de clientes
     * @return el modelo para la lista
     */
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

    /**
     * Edita el nombre del cliente en la tabla
     * @param cliente
     * @param cnuevo
     * @return true si fue exitoso, false en caso contrario
     * @throws Exception 
     */
    public boolean EditarCliente(String cliente, String cnuevo) throws Exception {
        try {
            conectarBase();
            setSql("UPDATE CLIENTES SET NOMBRECLIENTE= '" + cnuevo + "' WHERE NOMBRECLIENTE='" + cliente + "'");
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
            throw new Exception("Error al actualizar cliente");
        }
    }

    /**
     * Llena la tabla del historial del cliente
     * @param dm 
     */
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

    /**
     * Llena la tabla de devoluciones
     * @param dm 
     */
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

    /**
     * Inserta un nuevo cliente en la tabla Clientes
     * @return true si fue exitoso, false en caso contrario
     * @throws Exception 
     */
    boolean InsertarCliente() throws Exception {
        try {
            conectarBase();
            setSql("INSERT INTO CLIENTES (NOMBRECLIENTE, ACUMULADOCLIENTE) VALUES (?,?)");
            setPstmn(getConn().prepareStatement(getSql()));
            getPstmn().setString(1, nombreCliente);
            getPstmn().setFloat(2, acumuladoCliente);
            int res = getPstmn().executeUpdate();
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
            throw new Exception("Error al insertar cliente");
        }
    }

    /**
     * Borra un cliente de la tabla
     * @return true si fue exitoso, false en caso contrario
     * @throws Exception 
     */
    boolean BorrarCliente() throws Exception {
        try {
            conectarBase();
            setSql("DELETE CLIENTES WHERE NUMCLIENTE = ?");
            setPstmn(getConn().prepareStatement(getSql()));
            getPstmn().setInt(1, numCliente);
            int res = getPstmn().executeUpdate();
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
            throw new Exception("Error al borrar cliente");
        }
    }

}
