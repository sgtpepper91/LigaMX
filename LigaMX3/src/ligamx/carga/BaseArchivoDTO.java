
package ligamx.carga;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hector.lopez
 */
class BaseArchivoDTO {
    protected List<String> mensajes = new ArrayList<>();
    protected Integer numLinea;

    public List<String> getMensajes() {
        return mensajes;
    }

    public Integer getNumLinea() {
        return numLinea;
    }

    public void setNumLinea(Integer numLinea) {
        this.numLinea = numLinea;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }
    
    public String imprimirMensajes() {
        StringBuilder mensaBuilder = new StringBuilder();
        for (String mensaje : mensajes) {
            mensaBuilder.append("\t").append(mensaje).append("\n");
        }
        return mensaBuilder.toString();
    }
}
