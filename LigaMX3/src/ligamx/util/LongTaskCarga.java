package ligamx.util;

import java.awt.Dialog;
import java.awt.HeadlessException;
import java.io.File;
import javax.swing.JOptionPane;
import ligamx.carga.ReaderService;
import ligamx.carga.ReaderServiceImpl;

/**
 *
 * @author hector.lopez
 */
public class LongTaskCarga extends LongTaskImpl {

    private Dialog dialog;
    private ReaderService reader;
    File file;

    public LongTaskCarga(File file, ReaderServiceImpl reader) {
        lengthOfTask = 1;
        this.file = file;
        this.reader = reader;
    }

    //called from ProgressBarDemo to start the task
    @Override
    public void go() {
        current = 0;
        final SwingWorker worker = new SwingWorker() {
            @Override
            public Object construct() {
                try {
                    reader.leerArchivo(file, LongTaskCarga.this);
                    current = lengthOfTask;
                    return true;
                } catch (HeadlessException | ExcepcionAplicacion ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    current = lengthOfTask;
                    return null;
                }
            }
        };
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public ReaderService getReader() {
        return reader;
    }

    public void setReader(ReaderService reader) {
        this.reader = reader;
    }


}
