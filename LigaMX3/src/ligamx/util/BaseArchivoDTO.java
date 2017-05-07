
package ligamx.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hector.lopez
 */
class BaseArchivoDTO {
    protected List<String> mensajes = new ArrayList<>();

    public List<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }
}
