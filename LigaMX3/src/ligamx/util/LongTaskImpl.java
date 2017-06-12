
package ligamx.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hector.lopez
 */
public abstract class LongTaskImpl implements LongTask {

    protected static final Logger LOGGER = LogManager.getLogger();
    protected int lengthOfTask;
    protected int current = 0;
    protected String statMessage;

    public LongTaskImpl() {
    }

    //called from ProgressBarDemo to start the task
    @Override
    public abstract void go();

    //called from ProgressBarDemo to find out how much work needs to be done
    int getLengthOfTask() {
        return lengthOfTask;
    }

    //called from ProgressBarDemo to find out how much has been done
    int getCurrent() {
        return current;
    }

    @Override
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

    @Override
    public String getStatMessage() {
        return statMessage;
    }

    @Override
    public void setStatMessage(String statMessage) {
        this.statMessage = statMessage;
    }

}
