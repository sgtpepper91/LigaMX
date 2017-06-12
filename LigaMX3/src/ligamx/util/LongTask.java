package ligamx.util;

/**
 *
 * @author hector.lopez
 */
public interface LongTask {

    String getStatMessage();

    //called from ProgressBarDemo to start the task
    void go();

    void setCurrent(int current);

    void setStatMessage(String statMessage);
    
}
