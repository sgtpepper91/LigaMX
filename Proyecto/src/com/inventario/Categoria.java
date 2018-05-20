/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inventario;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hector.lopez
 */
public class Categoria extends ConexionBD{
    private Integer id;
    private String descripcion;

    Categoria(String descripcion) throws Excepcion {
        this.descripcion = descripcion;
        this.id = this.obtenerIdCategoria();
    }
    Categoria(Integer id) throws Excepcion {
        this.id = id;
        this.descripcion = this.obtenerDescripcion();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean insertarCategoria() throws Excepcion {
        Map<Integer, Object> params = new HashMap<>();
        setSql("INSERT INTO CATEGORIA (DESCRIPCION) VALUES (?)");
        params.put(1, descripcion.toUpperCase());
        return ejecutarUpdate(params);
    }
    
    private Integer obtenerIdCategoria() throws Excepcion {
        try{
            Integer idCat = null;
            if(null == this.descripcion || this.descripcion.isEmpty() || "TODAS".equals(this.descripcion)) {
                idCat = 0;
            } else {
                Map<Integer, Object> params = new HashMap<>();
                this.setSql("SELECT ID FROM CATEGORIA WHERE TRIM(UPPER(DESCRIPCION)) = ?");
                params.put(1, descripcion.trim().toUpperCase());
                this.ejecutarQuery(params);
                while (getRset().next()) {
                     idCat = getRset().getInt(1);
                }
                cerrarConexion();
            }
            return idCat;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }
    
    public boolean modificarCategoria() throws Excepcion {
        Map<Integer, Object> params = new HashMap<>();
        setSql("UPDATE CATEGORIA SET DESCRIPCION = ? WHERE ID = ?");
        params.put(1, descripcion.toUpperCase());
        params.put(2, id);
        return ejecutarUpdate(params);
    }

    private String obtenerDescripcion() throws Excepcion {
        try{
            String descripcionCat = null;
            if(null != this.id) {
                Map<Integer, Object> params = new HashMap<>();
                this.setSql("SELECT DESCRIPCION FROM CATEGORIA WHERE ID = ?");
                params.put(1, id);
                this.ejecutarQuery(params);
                while (getRset().next()) {
                     descripcionCat = getRset().getString(1);
                }
                cerrarConexion();
            }
            return descripcionCat;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }
}
