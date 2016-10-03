package com.inventario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import oracle.jdbc.driver.OracleDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author hecto
 */
public class ConexionBD {

    private Connection conn;
    private Statement stmn;
    private ResultSet rset;
    private PreparedStatement pstmn;
    private static String path;
    private static String usuario;
    private static String password;
    private String sql;
    private static Logger LOGGER = LogManager.getLogger();


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
     * @throws Excepcion
     */
    public void crearPreparedStatement() throws Excepcion {
        try {
            LOGGER.trace("Creando Prepared Statement");
            conectarBase();
            setPstmn(getConn().prepareStatement(sql));
        } catch (SQLException ex) {
            LOGGER.error(Constantes.ERROR_PREPARED_STMN + ex);
            throw new Excepcion(Constantes.ERROR_PREPARED_STMN);
        }
    }

    /**
     * Ejecuta INSERT, UPDATE o DELETE
     * @return true si fue exitoso, false en caso contrario
     * @throws Excepcion
     */
    public boolean ejecutarUpdate() throws Excepcion {
        try {
            boolean result;
            LOGGER.trace(Constantes.EJECUTANDO_QUERY.concat(getPstmn().toString()));
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
     * @throws Excepcion 
     */
    public void ejecutarQuery() throws Excepcion {
        try {
            crearPreparedStatement();
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
            DriverManager.registerDriver(new OracleDriver());
            //this.setConn(DriverManager.getConnection(path, usuario, password));
            this.setConn(DriverManager.getConnection(Constantes.URL_BD, Constantes.USER_BD, Constantes.PASS_BD));
            this.setStmn(conn.createStatement());
        } catch (SQLException ex) {
            LOGGER.error(Constantes.ERROR_CONEXION_BD.concat(ex.getMessage()));
            LOGGER.debug(Constantes.CONEXION_ERROR_QUERY.concat(ex.getMessage()));
            LOGGER.info(Constantes.CONEXION_ERROR_QUERY.concat(ex.getMessage()));
            LOGGER.warn(Constantes.CONEXION_ERROR_QUERY.concat(ex.getMessage()));
            LOGGER.fatal(Constantes.CONEXION_ERROR_QUERY.concat(ex.getMessage()));
            JOptionPane.showMessageDialog(null, Constantes.ERROR_CONEXION_BD, null, JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    /**
     * Cierra la conexión de la base de datos
     * @throws Excepcion 
     */
    public void cerrarConexion() throws Excepcion {
        try {
            getConn().close();
        } catch (SQLException ex) {
            LOGGER.error(Constantes.ERROR_CERRAR_CONN.concat(ex.getMessage()));
            throw new Excepcion(Constantes.ERROR_CERRAR_CONN.concat(ex.getMessage()));
        }
    }
    
    public Excepcion lanzarExcepcion(Exception ex) throws Excepcion {
        cerrarConexion();
        LOGGER.error(Constantes.CONEXION_ERROR_QUERY.concat(ex.getMessage()));
        Excepcion excepcion =  new Excepcion(Constantes.CONEXION_ERROR_QUERY.concat(ex.getMessage()));
        return excepcion;
    }
}
