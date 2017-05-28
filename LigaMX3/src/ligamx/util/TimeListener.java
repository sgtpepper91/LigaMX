package ligamx.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JProgressBar;
import javax.swing.Timer;

/**
 *
 * @author hector.lopez
 */
public class TimeListener implements ActionListener {

    private JProgressBar progressBar;
    private LongTask task;
    private Timer timer;

    public TimeListener(JProgressBar progressBar, LongTask task, Timer timer) {
        this.progressBar = progressBar;
        this.task = task;
        this.timer = timer;
    }

    public TimeListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        progressBar.setValue(task.getCurrent());
        if (task.done()) {
            timer.stop();
        }
    }

}
