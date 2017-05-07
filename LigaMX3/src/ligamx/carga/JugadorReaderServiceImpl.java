
package ligamx.carga;

import java.util.ArrayList;
import java.util.List;
import ligamx.dto.EquipoDTO;
import ligamx.dto.JugadorDTO;
import ligamx.service.EquipoService;
import ligamx.service.JugadorService;
import ligamx.service.impl.EquipoServiceImpl;
import ligamx.service.impl.JugadorServiceImpl;
import ligamx.util.ExcepcionAplicacion;

/**
 *
 * @author hector.lopez
 */
public class JugadorReaderServiceImpl extends ReaderServiceImpl<JugadorLecturaDTO, JugadorCargaDTO>{
    private final JugadorService jugadorService = new JugadorServiceImpl();
    private final EquipoService equipoService = new EquipoServiceImpl();

    @Override
    public List<JugadorCargaDTO> converter(List<JugadorLecturaDTO> source) throws ExcepcionAplicacion {
        List<JugadorCargaDTO> respuesta = new ArrayList<>();
        for (JugadorLecturaDTO elemSource : source) {
            JugadorCargaDTO elemRespuesta = new JugadorCargaDTO();
            elemRespuesta.setNombre(elemSource.getNombre());
            elemRespuesta.setEquipo(elemSource.getEquipo());
            elemRespuesta.setNumero(Integer.parseInt(elemSource.getNumero()));
            elemRespuesta.setNumLinea(elemSource.getNumLinea());
            respuesta.add(elemRespuesta);
        }
        
        return respuesta;
    }

    @Override
    public void validate(JugadorCargaDTO source) throws ExcepcionAplicacion {
        JugadorDTO jugadorDTO = jugadorService.buscarJugadorPorNombre(source.getNombre());
        if(null != jugadorDTO && null != jugadorDTO.getNombre()) {
            source.getMensajes().add("El jugador " + jugadorDTO.getNombre() + " ya existe");
        }
    }
    
    @Override
    public void write(List<JugadorCargaDTO> source) throws ExcepcionAplicacion {
        for (JugadorCargaDTO jugadorCargaDTO : source) {
            JugadorDTO jugadorDTO = new JugadorDTO();
            jugadorDTO.setNombre(jugadorCargaDTO.getNombre());
            jugadorDTO.setNumero(jugadorCargaDTO.getNumero());
            EquipoDTO equipoDTO = equipoService.buscarEquipoporNombre(jugadorCargaDTO.getEquipo());
        jugadorService.insertarJugador(jugadorDTO, equipoDTO.getIdEquipo());
        }
    }
}
