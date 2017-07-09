package com.inventario;

import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author hector.lopez
 */
public class LongTaskCargarPantalla extends LongTaskImpl {

    private PantallaCarga pantallaCarga;

    public LongTaskCargarPantalla() {
        //compute length of task ...
        //in a real program, this would figure out
        //the number of bytes to read or whatever
        lengthOfTask = 1;
    }

    //called from ProgressBarDemo to start the task
    @Override
    public void go() {
        current = 0;
        final SwingWorker worker = new SwingWorker() {
            @Override
            public Object construct() {
                try {
                    Inventario inventario = new Inventario(LongTaskCargarPantalla.this);
                    inventario.iniciar();
                    current = lengthOfTask;
                    inventario.setVisible(true);
                    pantallaCarga.setVisible(false);
                    return inventario;
                } catch (Exception ex) {
                    LOGGER.error(Arrays.toString(ex.getStackTrace()));
                    JOptionPane.showMessageDialog(pantallaCarga, ex, "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                    return null;
                }
            }
        };
    }

    public PantallaCarga getPantallaCarga() {
        return pantallaCarga;
    }

    public void setPantallaCarga(PantallaCarga pantallaCarga) {
        this.pantallaCarga = pantallaCarga;
    }

}
