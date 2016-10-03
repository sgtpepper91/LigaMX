package com.inventario;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
     *
     * @param total
     * @return true si fue exitoso, false en caso contrario
     * @throws com.inventario.Excepcion
     */
    public boolean ActualizarAcumulado(int total) throws Excepcion {
        try {
            setSql("UPDATE CLIENTES SET ACUMULADOCLIENTE = ? WHERE NUMCLIENTE = ?");
            crearPreparedStatement();
            getPstmn().setInt(1, acumuladoCliente + total);
            getPstmn().setInt(2, numCliente);
            return ejecutarUpdate();
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    /**
     * Recupera los datos del cliente
     *
     * @param nombre
     * @throws com.inventario.Excepcion
     */
    public void RecuperarCliente(String nombre) throws Excepcion {
        try {
            setSql("SELECT NUMCLIENTE, ACUMULADOCLIENTE FROM CLIENTES WHERE NOMBRECLIENTE = ?");
            crearPreparedStatement();
            getPstmn().setString(1, nombre);
            ejecutarQuery();
            while (getRset().next()) {
                numCliente = getRset().getInt(1);
                acumuladoCliente = getRset().getInt(2);
            }
            cerrarConexion();
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    /**
     * Llena la tabla de Clientes
     *
     * @param dm
     * @throws com.inventario.Excepcion
     */
    public void RecuperarCliente(DefaultTableModel dm) throws Excepcion {
        try {
            setSql("SELECT NOMBRECLIENTE, ACUMULADOCLIENTE FROM CLIENTES ORDER BY NOMBRECLIENTE");
            crearPreparedStatement();
            while (getRset().next()) {
                List row = new ArrayList();
                nombreCliente = getRset().getString(1);
                acumuladoCliente = getRset().getInt(2);
                row.add(nombreCliente);
                row.add(acumuladoCliente);
                dm.addRow(row.toArray());
            }
            cerrarConexion();
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    /**
     * Obtiene la lista de clientes
     *
     * @return el modelo para la lista
     * @throws com.inventario.Excepcion
     */
    public DefaultListModel getListaClientes() throws Excepcion {
        DefaultListModel listModel = new DefaultListModel();
        try {
            setSql("SELECT NOMBRECLIENTE FROM CLIENTES ORDER BY 1");
            crearPreparedStatement();
            while (getRset().next()) {
                listModel.addElement(getRset().getString("NOMBRECLIENTE"));
            }
            cerrarConexion();
            return listModel;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    /**
     * Edita el nombre del cliente en la tabla
     *
     * @param cliente
     * @param cnuevo
     * @return true si fue exitoso, false en caso contrario
     * @throws com.inventario.Excepcion
     */
    public boolean EditarCliente(String cliente, String cnuevo) throws Excepcion {
        try {
            setSql("UPDATE CLIENTES SET NOMBRECLIENTE= ? WHERE NOMBRECLIENTE= ? ");
            crearPreparedStatement();
            getPstmn().setString(1, cliente);
            getPstmn().setString(2, cnuevo);
            return ejecutarUpdate();
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    /**
     * Llena la tabla del historial del cliente
     *
     * @param dm
     * @throws com.inventario.Excepcion
     */
    public void ObtenerHistorial(DefaultTableModel dm) throws Excepcion {
        try {
            setSql("SELECT D.DETVENTACANTIDAD AS CANTIDAD , P.DESCRIPCIONPROD AS PRODUCTO, D.DETVENTASUBTOTAL AS TOTAL, V.FECHAVENTA AS FECHA "
                    + "FROM CLIENTES C INNER JOIN VENTAS V ON C.NUMCLIENTE=V.NUMCLIENTE "
                    + "INNER JOIN DETALLEVENTAS D ON V.NUMVENTA=D.NUMVENTA "
                    + "INNER JOIN PRODUCTOS P ON D.CLAVEPROD=P.CLAVEPROD "
                    + "WHERE C.NUMCLIENTE= ? UNION ALL "
                    + "SELECT NULL AS CANTIDAD, 'Pago' AS TOTAL, A.PAGO AS TOTAL, A.FECHA "
                    + "FROM PAGOS A INNER JOIN CLIENTES C "
                    + "ON C.NUMCLIENTE=A.NUMCLIENTE "
                    + "WHERE C.NUMCLIENTE= ? ORDER BY FECHA DESC");
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
                row.add(df.format(fecha));
                dm.addRow(row.toArray());
            }
            cerrarConexion();
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    /**
     * Llena la tabla de devoluciones
     *
     * @param dm
     * @throws com.inventario.Excepcion
     */
    public void ObtenerHistorialDevolucion(DefaultTableModel dm) throws Excepcion {
        try {
            setSql("SELECT D.DETVENTACANTIDAD AS CANTIDAD , P.DESCRIPCIONPROD AS PRODUCTO, D.DETVENTASUBTOTAL AS TOTAL, V.FECHAVENTA AS FECHA, V.NUMVENTA, P.CLAVEPROD "
                    + "FROM CLIENTES C INNER JOIN VENTAS V ON C.NUMCLIENTE=V.NUMCLIENTE "
                    + "INNER JOIN DETALLEVENTAS D ON V.NUMVENTA=D.NUMVENTA "
                    + "INNER JOIN PRODUCTOS P ON D.CLAVEPROD=P.CLAVEPROD "
                    + "WHERE C.NUMCLIENTE= ? AND P.DESCRIPCIONPROD !='Ajuste' ORDER BY FECHA DESC");
            crearPreparedStatement();
            getPstmn().setInt(1, numCliente);
            while (getRset().next()) {
                int cantidad = getRset().getInt(1);
                String descripcion = getRset().getString(2);
                float total = getRset().getFloat(3);
                java.util.Date fecha = getRset().getDate(4);
                int numVenta = getRset().getInt(5);
                int claveProd = getRset().getInt(6);
                List row = new ArrayList();
                row.add(cantidad);
                row.add(descripcion);
                row.add(total);
                row.add(df.format(fecha));
                row.add(numVenta);
                row.add(claveProd);
                dm.addRow(row.toArray());
            }
            cerrarConexion();
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    /**
     * Inserta un nuevo cliente en la tabla Clientes
     *
     * @return true si fue exitoso, false en caso contrario
     * @throws com.inventario.Excepcion
     */
    boolean InsertarCliente() throws Excepcion{
        try {
            setSql("INSERT INTO CLIENTES (NOMBRECLIENTE, ACUMULADOCLIENTE) VALUES (?,?)");
            crearPreparedStatement();
            getPstmn().setString(1, nombreCliente);
            getPstmn().setFloat(2, acumuladoCliente);
            return ejecutarUpdate();
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    /**
     * Borra un cliente de la tabla
     *
     * @return true si fue exitoso, false en caso contrario
     * * @throws com.inventario.Excepcion
     */
    boolean BorrarCliente() throws Excepcion {
        try {
            setSql("DELETE CLIENTES WHERE NUMCLIENTE = ?");
            crearPreparedStatement();
            getPstmn().setInt(1, numCliente);
            return ejecutarUpdate();
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }
}
