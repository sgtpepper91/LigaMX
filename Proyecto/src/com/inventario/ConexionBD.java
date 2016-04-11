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
    private final String path;
    private final String usuario;
    private final String password;
    private String sql;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection aConn) {
        conn = aConn;
    }

    public ConexionBD(String path, String usuario, String password) {
        this.path = path;
        this.usuario = usuario;
        this.password = password;
    }

    public Statement getStmn() {
        return stmn;
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
        //conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "inventario", "1234");
        this.setConn(DriverManager.getConnection(path, usuario, password));
        this.setStmn(conn.createStatement());
    }
}
