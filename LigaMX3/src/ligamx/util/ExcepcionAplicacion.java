
package ligamx.util;

/**
 *
 * @author hector.lopez
 */
public class ExcepcionAplicacion extends Exception{

    public ExcepcionAplicacion() {
    }

    public ExcepcionAplicacion(String message) {
        super(message);
    }

    public ExcepcionAplicacion(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcepcionAplicacion(Throwable cause) {
        super(cause);
    }

    public ExcepcionAplicacion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
