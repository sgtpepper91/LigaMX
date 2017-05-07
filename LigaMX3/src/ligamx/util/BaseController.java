package ligamx.util;

import java.awt.Image;
import java.awt.Toolkit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hector.lopez
 */
public class BaseController extends javax.swing.JFrame {
    protected static final Logger LOGGER = LogManager.getLogger();
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("ligamx/resources/images/liga_mx.png"));
        return retValue;
    }
    
    final public void setIcono(){
        setIconImage(getIconImage());
    }
}
