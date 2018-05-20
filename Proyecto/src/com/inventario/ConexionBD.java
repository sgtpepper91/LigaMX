package com.inventario;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.swing.JOptionPane;
import oracle.jdbc.driver.OracleDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author hecto
 */
public class ConexionBD {

    protected Connection conn;
    protected Statement stmn;
    protected ResultSet rset;
    protected PreparedStatement pstmn;
    private static String path;
    private static String usuario;
    private static String password;
    protected String sql;
    protected Logger LOGGER = LogManager.getLogger(getClass());

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection aConn) {
        conn = aConn;
    }

    public void IniciaConexion(String path, String usuario, String password) {
        ConexionBD.path = path;
        ConexionBD.usuario = usuario;
        ConexionBD.password = password;
    }

    public Statement getStmn() {
        return stmn;
    }

    public static String getPath() {
        return path;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static String getPassword() {
        return password;
    }

    public void setStmn(Statement aStmn) {
        stmn = aStmn;
    }

    public ResultSet getRset() {
        return rset;
    }

    public void setRset(ResultSet aRset) {
        rset = aRset;
    }

    public PreparedStatement getPstmn() {
        return pstmn;
    }

    public void setPstmn(PreparedStatement aPstmn) {
        pstmn = aPstmn;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    /**
     * Crea Prepared Statement con el sql
     *
     * @param params
     * @throws Excepcion
     */
    private void crearPreparedStatement(Map params) throws Excepcion {
        try {
            LOGGER.info("Creando Prepared Statement: " + sql);
            conectarBase();
            setPstmn(getConn().prepareStatement(sql));
            if (!params.isEmpty()) {
                for (int i = 1; i <= params.size(); i++) {
                    if (params.get(i) instanceof String) {
                        getPstmn().setString(i, (String) params.get(i));
                    } else if (params.get(i) instanceof Integer) {
                        getPstmn().setInt(i, (int) params.get(i));
                    } else if (params.get(i) instanceof Float) {
                        getPstmn().setFloat(i, (float) params.get(i));
                    } else if (params.get(i) instanceof Date) {
                        getPstmn().setDate(i, (Date) params.get(i));
                    } else if (params.get(i) instanceof BigDecimal) {
                        getPstmn().setBigDecimal(i, (BigDecimal) params.get(i));
                    }
                }
            }
        } catch (SQLException ex) {
            LOGGER.error(Constantes.ERROR_PREPARED_STMN + ex);
            throw new Excepcion(Constantes.ERROR_PREPARED_STMN);
        }
    }

    /**
     * Ejecuta INSERT, UPDATE o DELETE
     *
     * @param params
     * @return true si fue exitoso, false en caso contrario
     * @throws Excepcion
     */
    public boolean ejecutarUpdate(Map params) throws Excepcion {
        try {
            boolean result;
            crearPreparedStatement(params);
            LOGGER.info(Constantes.EJECUTANDO_QUERY.concat(params.toString()));
            int rows = getPstmn().executeUpdate();
            if (rows > 0) {
                result = Boolean.TRUE;
            } else {
                result = Boolean.FALSE;
            }
            cerrarConexion();
            return result;
        } catch (SQLException ex) {
            LOGGER.error(Constantes.CONEXION_ERROR_QUERY.concat(ex.getMessage()));
            throw new Excepcion(Constantes.CONEXION_ERROR_QUERY.concat(ex.getMessage()));
        }
    }

    /**
     * Ejecuta QUERY
     *
     * @param params
     * @throws Excepcion
     */
    public void ejecutarQuery(Map params) throws Excepcion {
        try {
            crearPreparedStatement(params);
            LOGGER.info(Constantes.EJECUTANDO_QUERY.concat(params.toString()));
            setRset(getPstmn().executeQuery());
        } catch (SQLException ex) {
            LOGGER.error(Constantes.CONEXION_ERROR_QUERY.concat(ex.getMessage()));
            throw new Excepcion(Constantes.CONEXION_ERROR_QUERY.concat(ex.getMessage()));
        }
    }

    /**
     * Establece la conexión a la base de datos
     */
    private void conectarBase() {
        try {
            LOGGER.debug("Creando conexión");
            DriverManager.registerDriver(new OracleDriver());
            //this.setConn(DriverManager.getConnection(path, usuario, password));
            this.setConn(DriverManager.getConnection(Constantes.URL_BD, Constantes.USER_BD, Constantes.PASS_BD));
            this.setStmn(conn.createStatement());
        } catch (SQLException ex) {
            LOGGER.error(Constantes.ERROR_CONEXION_BD.concat(ex.getMessage()));
            JOptionPane.showMessageDialog(null, Constantes.ERROR_CONEXION_BD, null, JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Cierra la conexión de la base de datos
     *
     * @throws Excepcion
     */
    public void cerrarConexion() throws Excepcion {
        try {
            LOGGER.debug("Cerrando conexión");
            getConn().close();
        } catch (SQLException ex) {
            LOGGER.error(Constantes.ERROR_CERRAR_CONN.concat(ex.getMessage()));
            throw new Excepcion(Constantes.ERROR_CERRAR_CONN.concat(ex.getMessage()));
        }
    }

    public Excepcion lanzarExcepcion(Exception ex) throws Excepcion {
        cerrarConexion();
        LOGGER.error(Constantes.CONEXION_ERROR_QUERY.concat(ex.getMessage()), ex);
        Excepcion excepcion = new Excepcion(Constantes.CONEXION_ERROR_QUERY.concat(ex.getMessage()));
        return excepcion;
    }
}
