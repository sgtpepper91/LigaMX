package com.inventario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.driver.OracleDriver;

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

    public void conectarBase() throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        //this.setConn(DriverManager.getConnection(path, usuario, password));
        this.setConn(DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "inventario", "1234"));
        this.setStmn(conn.createStatement());
    }
}