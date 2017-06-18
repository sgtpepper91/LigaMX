package ligamx.carga;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import ligamx.dto.EquipoDTO;
import ligamx.dto.PartidoDTO;
import ligamx.service.EquipoService;
import ligamx.service.PartidoService;
import ligamx.service.impl.EquipoServiceImpl;
import ligamx.service.impl.PartidoServiceImpl;
import ligamx.util.ExcepcionAplicacion;

/**
 *
 * @author hector.lopez
 */
public class PartidoReaderServiceImpl extends ReaderServiceImpl<PartidoLecturaDTO, PartidoCargaDTO> {

    private final PartidoService partidoService = new PartidoServiceImpl();
    private final EquipoService equipoService = new EquipoServiceImpl();

    @Override
    public List<PartidoCargaDTO> converter(List<PartidoLecturaDTO> source) throws ExcepcionAplicacion {
        List<PartidoCargaDTO> respuesta = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (PartidoLecturaDTO elemSource : source) {
            PartidoCargaDTO elemRespuesta = new PartidoCargaDTO();
            elemRespuesta.setJornada(Integer.parseInt(elemSource.getJornada()));
            elemRespuesta.setLocal(elemSource.getLocal());
            elemRespuesta.setVisitante(elemSource.getVisitante());
            try {
                elemRespuesta.setFecha(sdf.parse(elemSource.getFecha()));
            } catch (ParseException ex) {
                elemSource.getMensajes().add("La fecha es inválida.");
            }
            elemRespuesta.setNumLinea(elemSource.getNumLinea());
            respuesta.add(elemRespuesta);
        }
        return respuesta;
    }

    @Override
    public void validate(PartidoCargaDTO source) throws ExcepcionAplicacion {
        EquipoDTO local = equipoService.buscarEquipoporNombre(source.getLocal());
        EquipoDTO visitante = equipoService.buscarEquipoporNombre(source.getVisitante());
        PartidoDTO partido1DTO = partidoService.buscarPartidoporEquipos(local.getIdEquipo(), visitante.getIdEquipo());
        PartidoDTO partido2DTO = partidoService.buscarPartidoporEquipos(visitante.getIdEquipo(), local.getIdEquipo());
        if (null != partido1DTO.getIdPartido() || null != partido2DTO.getIdPartido()) {
            source.getMensajes().add("El partido ya existe.");
        }
    }

    @Override
    public void write(List<PartidoCargaDTO> source) throws ExcepcionAplicacion {
        for (PartidoCargaDTO partidoCargaDTO : source) {
            EquipoDTO local = equipoService.buscarEquipoporNombre(partidoCargaDTO.getLocal());
            EquipoDTO visitante = equipoService.buscarEquipoporNombre(partidoCargaDTO.getVisitante());
            PartidoDTO partidoDTO = new PartidoDTO();
            partidoDTO.setJornada(partidoCargaDTO.getJornada());
            partidoDTO.setIdLocal(local.getIdEquipo());
            partidoDTO.setIdVisitante(visitante.getIdEquipo());
            partidoDTO.setFecha(partidoCargaDTO.getFecha());
            partidoService.insertarPartido(partidoDTO);
        }
    }

}
