/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventario;

/**
 *
 * @author hecto
 */
public class Excepcion extends Exception {

    public Excepcion() {
    }

    public Excepcion(String message) {
        super(message);
    }

    public Excepcion(String message, Throwable cause) {
        super(message, cause);
    }

    public Excepcion(Throwable cause) {
        super(cause);
    }

    public Excepcion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
