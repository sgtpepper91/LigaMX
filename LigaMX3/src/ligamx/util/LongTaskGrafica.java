package ligamx.util;

import java.awt.Dialog;
import java.util.Arrays;
import javax.swing.JOptionPane;
import ligamx.controller.Grafica;
import ligamx.controller.LigaMXController;

/**
 *
 * @author hector.lopez
 */
public class LongTaskGrafica extends LongTaskImpl {
    private LigaMXController ligaMX;
    private Dialog dialog;

    public LongTaskGrafica() {
        //compute length of task ...
        //in a real program, this would figure out
        //the number of bytes to read or whatever
        lengthOfTask = 324;
    }

    //called from ProgressBarDemo to start the task
    @Override
    public void go() {
        current = 0;
        final SwingWorker worker = new SwingWorker() {
            @Override
            public Object construct() {
                try {
                    Grafica grafica = new Grafica(LongTaskGrafica.this);
                    grafica.llenarTabla();
                    current = lengthOfTask;
                    JOptionPane.showMessageDialog(ligaMX, grafica, "Tabla jornadas", JOptionPane.PLAIN_MESSAGE);
                    return true;
                } catch (Exception ex) {
                    LOGGER.error(Arrays.toString(ex.getStackTrace()));
                    JOptionPane.showMessageDialog(ligaMX, ex, "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                    return null;
                }
            }
        };
    }

    public LigaMXController getLigaMX() {
        return ligaMX;
    }

    public void setLigaMX(LigaMXController ligaMX) {
        this.ligaMX = ligaMX;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

}
