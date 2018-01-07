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
public class JugadorReaderServiceImpl extends ReaderServiceImpl<JugadorLecturaDTO, JugadorCargaDTO> {

    private final JugadorService jugadorService = new JugadorServiceImpl();
    private final EquipoService equipoService = new EquipoServiceImpl();

    @Override
    public List<JugadorCargaDTO> converter(List<JugadorLecturaDTO> source) throws ExcepcionAplicacion {
        List<JugadorCargaDTO> respuesta = new ArrayList<>();
        for (JugadorLecturaDTO elemSource : source) {
            JugadorCargaDTO elemRespuesta = new JugadorCargaDTO();
            elemRespuesta.setNombre(elemSource.getNombre().trim());
            elemRespuesta.setEquipo(elemSource.getEquipo());
            elemRespuesta.setNumero(Integer.parseInt(elemSource.getNumero()));
            elemRespuesta.setNumLinea(elemSource.getNumLinea());
            respuesta.add(elemRespuesta);
        }

        return respuesta;
    }

    @Override
    public void validate(JugadorCargaDTO source) throws ExcepcionAplicacion {

    }

    @Override
    public void write(List<JugadorCargaDTO> source) throws ExcepcionAplicacion {
        for (JugadorCargaDTO jugadorCargaDTO : source) {
            JugadorDTO jugadorDTO;
            jugadorDTO = jugadorService.buscarJugadorPorNombre(jugadorCargaDTO.getNombre());
            EquipoDTO equipoDTO = equipoService.buscarEquipoporNombre(jugadorCargaDTO.getEquipo());
            List<JugadorDTO> jugadores = jugadorService.buscarJugadores(equipoDTO);
            jugadorDTO.setNumero(jugadorCargaDTO.getNumero());
            if (null != jugadorDTO.getNombre()) {
                if (!jugadores.contains(jugadorDTO)) {
                    jugadorService.modificarJugador(jugadorDTO, equipoDTO.getIdEquipo());
                }
            } else {
                jugadorDTO.setNombre(jugadorCargaDTO.getNombre());
                jugadorService.insertarJugador(jugadorDTO, equipoDTO.getIdEquipo());
            }
        }
    }
}
