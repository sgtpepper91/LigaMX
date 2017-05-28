package ligamx.util;

import javax.swing.SwingUtilities;

/**
 *
 * @author hector.lopez
 */
abstract class SwingWorker {

    private Object value;
    private Thread thread;

    /**
     * Compute the value to be returned by the <code>get</code> method.
     */
    public abstract Object construct();

    /**
     * Called on the event dispatching thread (not on the worker thread) after
     * the <code>construct</code> method has returned.
     */
    public void finished() {
    }

    /**
     * Return the value created by the <code>construct</code> method.
     */
    public Object get() {
        while (true) {  // keep trying if we're interrupted
            Thread t;
            synchronized (SwingWorker.this) {
                t = thread;
                if (t == null) {
                    return value;
                }
            }
            try {
                t.join();
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Start a thread that will call the <code>construct</code> method and then
     * exit.
     */
    @SuppressWarnings("CallToThreadStartDuringObjectConstruction")
    public SwingWorker() {
        final Runnable doFinished = this::finished;

        Runnable doConstruct = () -> {
            synchronized (SwingWorker.this) {
                value = construct();
                thread = null;
            }
            SwingUtilities.invokeLater(doFinished);
        };

        thread = new Thread(doConstruct);
        thread.start();
    }
}
