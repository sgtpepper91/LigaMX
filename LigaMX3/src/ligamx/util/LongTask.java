package ligamx.util;

import java.util.Arrays;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import ligamx.controller.LigaMXController;
import ligamx.controller.PantallaCarga;
import static ligamx.util.BaseGeneral.LOGGER;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author hector.lopez
 */
public class LongTask {
    protected static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private PantallaCarga pantallaCarga;
    private final int lengthOfTask;
    private int current = 0;
    private String statMessage;

    public LongTask() {
        //compute length of task ...
        //in a real program, this would figure out
        //the number of bytes to read or whatever
        lengthOfTask = 1000;
    }

    //called from ProgressBarDemo to start the task
    public void go() {
        current = 0;
        final SwingWorker worker = new SwingWorker() {
            @Override
            public Object construct() {
                try {
                    LigaMXController ligaMX = new LigaMXController(LongTask.this);
                    ligaMX.iniciar();
                    ligaMX.setVisible(true);
                    return ligaMX;
                } catch (Exception ex) {
                    LOGGER.error(Arrays.toString(ex.getStackTrace()));
                    JOptionPane.showMessageDialog(pantallaCarga, ex, "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                    return null;
                }
            }
        };
    }

    //called from ProgressBarDemo to find out how much work needs to be done
    int getLengthOfTask() {
        return lengthOfTask;
    }

    //called from ProgressBarDemo to find out how much has been done
    int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    void stop() {
        current = lengthOfTask;
    }

    //called from ProgressBarDemo to find out if the task has completed
    boolean done() {
        return current >= lengthOfTask;
    }

    String getMessage() {
        return statMessage;
    }

    public PantallaCarga getPantallaCarga() {
        return pantallaCarga;
    }

    public void setPantallaCarga(PantallaCarga pantallaCarga) {
        this.pantallaCarga = pantallaCarga;
    }

    public String getStatMessage() {
        return statMessage;
    }

    public void setStatMessage(String statMessage) {
        this.statMessage = statMessage;
    }

    
}
