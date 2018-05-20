package com.inventario;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Map<Integer, Object> params = new HashMap<>();
        setSql("UPDATE CLIENTES SET ACUMULADOCLIENTE = ? WHERE NUMCLIENTE = ?");
        params.put(1, acumuladoCliente + total);
        params.put(2, numCliente);
        return ejecutarUpdate(params);
    }

    /**
     * Recupera los datos del cliente
     *
     * @param nombre
     * @throws com.inventario.Excepcion
     */
    public void RecuperarCliente(String nombre) throws Excepcion {
        try {
            Map<Integer, Object> params = new HashMap<>();
            setSql("SELECT NUMCLIENTE, ACUMULADOCLIENTE FROM CLIENTES WHERE NOMBRECLIENTE = ?");
            params.put(1, nombre);
            ejecutarQuery(params);
            while (getRset().next()) {
                numCliente = getRset().getInt(1);
                acumuladoCliente = getRset().getInt(2);
                nombreCliente = nombre;
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
            Map<Integer, Object> params = new HashMap<>();
            setSql("SELECT NOMBRECLIENTE, ACUMULADOCLIENTE FROM CLIENTES ORDER BY NOMBRECLIENTE");
            ejecutarQuery(params);
            while (getRset().next()) {
                List<Object> row = new ArrayList<>();
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
    public DefaultListModel<String> getListaClientes() throws Excepcion {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        try {
            Map<Integer, Object> params = new HashMap<>();
            setSql("SELECT NOMBRECLIENTE FROM CLIENTES ORDER BY 1");
            ejecutarQuery(params);
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
     * @param cnuevo
     * @return true si fue exitoso, false en caso contrario
     * @throws com.inventario.Excepcion
     */
    public boolean EditarCliente(String cnuevo) throws Excepcion {
        Map<Integer, Object> params = new HashMap<>();
        setSql("UPDATE CLIENTES SET NOMBRECLIENTE= ? WHERE NUMCLIENTE= ? ");
        params.put(1, cnuevo);
        params.put(2, numCliente);
        return ejecutarUpdate(params);
    }

    /**
     * Llena la tabla del historial del cliente
     *
     * @param dm
     * @throws com.inventario.Excepcion
     */
    public void ObtenerHistorial(DefaultTableModel dm) throws Excepcion {
        try {
            Map<Integer, Object> params = new HashMap<>();
            setSql("SELECT D.DETVENTACANTIDAD AS CANTIDAD , P.DESCRIPCIONPROD AS PRODUCTO, D.DETVENTASUBTOTAL AS TOTAL, V.FECHAVENTA AS FECHA "
                    + "FROM CLIENTES C INNER JOIN VENTAS V ON C.NUMCLIENTE=V.NUMCLIENTE "
                    + "INNER JOIN DETALLEVENTAS D ON V.NUMVENTA=D.NUMVENTA "
                    + "INNER JOIN PRODUCTOS P ON D.CLAVEPROD=P.CLAVEPROD "
                    + "WHERE C.NUMCLIENTE= ? UNION ALL "
                    + "SELECT NULL AS CANTIDAD, 'Pago' AS TOTAL, A.PAGO AS TOTAL, A.FECHA "
                    + "FROM PAGOS A INNER JOIN CLIENTES C "
                    + "ON C.NUMCLIENTE=A.NUMCLIENTE "
                    + "WHERE C.NUMCLIENTE= ? ORDER BY FECHA DESC");
            params.put(1, numCliente);
            params.put(2, numCliente);
            ejecutarQuery(params);
            while (getRset().next()) {
                int cantidad = getRset().getInt(1);
                String descripcion = getRset().getString(2);
                float total = getRset().getFloat(3);
                java.util.Date fecha = getRset().getDate(4);
                List<Object> row = new ArrayList<>();
                row.add(cantidad);
                row.add(descripcion);
                row.add(total);
                //row.add(df.format(fecha));
                row.add(fecha);
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
            Map<Integer, Object> params = new HashMap<>();
            setSql("SELECT D.DETVENTACANTIDAD AS CANTIDAD , P.DESCRIPCIONPROD AS PRODUCTO, D.DETVENTASUBTOTAL AS TOTAL, V.FECHAVENTA AS FECHA, V.NUMVENTA, P.CLAVEPROD "
                    + "FROM CLIENTES C INNER JOIN VENTAS V ON C.NUMCLIENTE=V.NUMCLIENTE "
                    + "INNER JOIN DETALLEVENTAS D ON V.NUMVENTA=D.NUMVENTA "
                    + "INNER JOIN PRODUCTOS P ON D.CLAVEPROD=P.CLAVEPROD "
                    + "WHERE C.NUMCLIENTE= ? AND P.DESCRIPCIONPROD !='Ajuste' ORDER BY FECHA DESC");
            params.put(1, numCliente);
            ejecutarQuery(params);
            while (getRset().next()) {
                int cantidad = getRset().getInt(1);
                String descripcion = getRset().getString(2);
                float total = getRset().getFloat(3);
                java.util.Date fecha = getRset().getDate(4);
                int numVenta = getRset().getInt(5);
                int claveProd = getRset().getInt(6);
                List<Object> row = new ArrayList<>();
                row.add(cantidad);
                row.add(descripcion);
                row.add(total);
                row.add(fecha);
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
        Map<Integer, Object> params = new HashMap<>();
        setSql("INSERT INTO CLIENTES (NOMBRECLIENTE, ACUMULADOCLIENTE) VALUES (?,?)");
        params.put(1, nombreCliente);
        params.put(2, acumuladoCliente);
        return ejecutarUpdate(params);
    }

    /**
     * Borra un cliente de la tabla
     *
     * @return true si fue exitoso, false en caso contrario
     * * @throws com.inventario.Excepcion
     */
    boolean BorrarCliente() throws Excepcion {
        Map<Integer, Object> params = new HashMap<>();
        setSql("DELETE CLIENTES WHERE NUMCLIENTE = ?");
        params.put(1, numCliente);
        return ejecutarUpdate(params);
    }
}
