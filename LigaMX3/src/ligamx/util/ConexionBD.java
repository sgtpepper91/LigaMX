package ligamx.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;
import javax.swing.JOptionPane;
import oracle.jdbc.driver.OracleDriver;

/**
 *
 * @author hector.lopez
 */
public class ConexionBD extends BaseGeneral {

    private Connection conn;
    private Statement stmn;
    private ResultSet rset;
    private PreparedStatement pstmn;
    private static String path;
    private static String usuario;
    private static String password;
    private StringBuilder sql;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection aConn) {
        conn = aConn;
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

    public StringBuilder getSql() {
        return sql;
    }

    public void setSql(StringBuilder sql) {
        this.sql = sql;
    }

    /**
     * Abre la conexión a la base de datos
     *
     * @param path
     * @param usuario
     * @param password
     */
    public void abrirConexion(String path, String usuario, String password) {
        ConexionBD.path = path;
        ConexionBD.usuario = usuario;
        ConexionBD.password = password;
    }

    /**
     * Crea Prepared Statement con el sql
     *
     * @param params
     * @throws Excepcion
     */
    private void crearPreparedStatement(Map params) throws ExcepcionAplicacion {
        try {
            LOGGER.info(Constantes.CREANDO_PREPARED_STMN + sql);
            conectarBase();
            setPstmn(getConn().prepareStatement(sql.toString()));
            if (!params.isEmpty()) {
                for (int i = 1; i <= params.size(); i++) {
                    if (params.get(i) instanceof String || params.get(i) instanceof Character) {
                        getPstmn().setString(i, (String) params.get(i).toString());
                    } else if (params.get(i) instanceof Integer) {
                        getPstmn().setInt(i, (Integer) params.get(i));
                    } else if (params.get(i) instanceof Double) {
                        getPstmn().setDouble(i, (Double) params.get(i));
                    } else if (params.get(i) instanceof Date) {
                        getPstmn().setTimestamp(i, new java.sql.Timestamp(((Date) params.get(i)).getTime()));
                    }
                }
            }
        } catch (SQLException ex) {
            LOGGER.error(Constantes.ERROR_PREPARED_STMN + ex);
            throw new ExcepcionAplicacion(Constantes.ERROR_PREPARED_STMN);
        }
    }

    /**
     * Ejecuta INSERT, UPDATE o DELETE
     *
     * @param params
     * @return true si fue exitoso, false en caso contrario
     * @throws ligamx.util.ExcepcionAplicacion
     */
    public boolean ejecutarUpdate(Map params) throws ExcepcionAplicacion {
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
            throw new ExcepcionAplicacion(Constantes.CONEXION_ERROR_QUERY.concat(ex.getMessage()));
        }
    }

    /**
     * Ejecuta QUERY
     *
     * @param params
     * @throws ligamx.util.ExcepcionAplicacion
     */
    public void ejecutarQuery(Map params) throws ExcepcionAplicacion {
        try {
            crearPreparedStatement(params);
            LOGGER.info(Constantes.EJECUTANDO_QUERY.concat(params.toString()));
            setRset(getPstmn().executeQuery());
        } catch (SQLException ex) {
            LOGGER.error(Constantes.CONEXION_ERROR_QUERY.concat(ex.getMessage()));
            throw new ExcepcionAplicacion(Constantes.CONEXION_ERROR_QUERY.concat(ex.getMessage()));
        }
    }

    /**
     * Establece la conexión a la base de datos
     */
    private void conectarBase() {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(Constantes.CREANDO_CONEXION);
            }
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
     * @throws ligamx.util.ExcepcionAplicacion
     */
    public void cerrarConexion() throws ExcepcionAplicacion {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(Constantes.CERRANDO_CONEXION);
            }
            getConn().close();
        } catch (SQLException ex) {
            LOGGER.error(Constantes.ERROR_CERRAR_CONN.concat(ex.getMessage()));
            throw new ExcepcionAplicacion(Constantes.ERROR_CERRAR_CONN.concat(ex.getMessage()));
        }
    }

    public ExcepcionAplicacion lanzarExcepcion(Exception ex) throws ExcepcionAplicacion {
        cerrarConexion();
        LOGGER.error(Constantes.CONEXION_ERROR_QUERY.concat(ex.getMessage()));
        ExcepcionAplicacion excepcion = new ExcepcionAplicacion(Constantes.CONEXION_ERROR_QUERY.concat(ex.getMessage()));
        return excepcion;
    }
}
