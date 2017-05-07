
package ligamx.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hector.lopez
 */
public class JugadorReaderServiceImpl extends ReaderServiceImpl<JugadorLecturaDTO, JugadorCargaDTO>{

    @Override
    public List<JugadorCargaDTO> converter(List<JugadorLecturaDTO> source) throws ExcepcionAplicacion {
        List<JugadorCargaDTO> respuesta = new ArrayList<>();
        for (JugadorLecturaDTO elemSource : source) {
            JugadorCargaDTO elemRespuesta = new JugadorCargaDTO();
            elemRespuesta.setNombre(elemSource.getNombre());
            elemRespuesta.setEquipo(elemSource.getEquipo());
            elemRespuesta.setNumero(Integer.parseInt(elemSource.getNumero()));
            elemRespuesta.setMensajes(elemSource.getMensajes());
            respuesta.add(elemRespuesta);
        }
        
        return respuesta;
    }

    @Override
    public List<JugadorCargaDTO> validate(List<JugadorCargaDTO> source) throws ExcepcionAplicacion {
        return null;
    }

}
