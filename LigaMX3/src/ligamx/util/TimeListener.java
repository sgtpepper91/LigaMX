package ligamx.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.Timer;

/**
 *
 * @author hector.lopez
 */
public class TimeListener<T extends LongTaskImpl> implements ActionListener {

    private JProgressBar progressBar;
    private LongTaskImpl task;
    private Timer timer;
    JDialog dialog;

    public TimeListener(JProgressBar progressBar, T task) {
        this.progressBar = progressBar;
        this.task = task;
    }

    public TimeListener() {
    }

    public TimeListener(JProgressBar progressBar, LongTaskGrafica task, JDialog dialog) {
        this.progressBar = progressBar;
        this.task = task;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!progressBar.isIndeterminate()) {
            progressBar.setValue((int) (100.0 * task.getCurrent() / task.getLengthOfTask()));
        }
        if (task.done()) {
            timer.stop();
            if (null != dialog) {
                dialog.dispose();
            }
        }
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

}
