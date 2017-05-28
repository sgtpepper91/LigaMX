package ligamx.carga;

import java.util.ArrayList;
import java.util.List;
import ligamx.dto.CocienteDTO;
import ligamx.dto.EquipoDTO;
import ligamx.service.CocienteService;
import ligamx.service.impl.CocienteServiceImpl;
import ligamx.util.ExcepcionAplicacion;

/**
 *
 * @author hector.lopez
 */
public class CocienteReaderServiceImpl extends ReaderServiceImpl<CocienteLecturaDTO, CocienteCargaDTO> {

    private final CocienteService cocienteService = new CocienteServiceImpl();

    @Override
    public List<CocienteCargaDTO> converter(List<CocienteLecturaDTO> source) throws ExcepcionAplicacion {
        List<CocienteCargaDTO> respuesta = new ArrayList<>();
        for (CocienteLecturaDTO elemSource : source) {
            CocienteCargaDTO elemRespuesta = new CocienteCargaDTO();
            elemRespuesta.setJa15(Integer.parseInt(elemSource.getJa15()));
            elemRespuesta.setJc16(Integer.parseInt(elemSource.getJc16()));
            elemRespuesta.setJa16(Integer.parseInt(elemSource.getJa16()));
            elemRespuesta.setJc17(Integer.parseInt(elemSource.getJc17()));
            elemRespuesta.setJa17(Integer.parseInt(elemSource.getJa17()));
            elemRespuesta.setJc18(Integer.parseInt(elemSource.getJc18()));
            elemRespuesta.setTj(Integer.parseInt(elemSource.getTj()));
            elemRespuesta.setPa15(Integer.parseInt(elemSource.getPa15()));
            elemRespuesta.setPc16(Integer.parseInt(elemSource.getPc16()));
            elemRespuesta.setPa16(Integer.parseInt(elemSource.getPa16()));
            elemRespuesta.setPc17(Integer.parseInt(elemSource.getPc17()));
            elemRespuesta.setPa17(Integer.parseInt(elemSource.getPa17()));
            elemRespuesta.setPc18(Integer.parseInt(elemSource.getPc18()));
            elemRespuesta.setTp(Integer.parseInt(elemSource.getTp()));
            elemRespuesta.setDga15(Integer.parseInt(elemSource.getDga15()));
            elemRespuesta.setDgc16(Integer.parseInt(elemSource.getDgc16()));
            elemRespuesta.setDga16(Integer.parseInt(elemSource.getDga16()));
            elemRespuesta.setDgc17(Integer.parseInt(elemSource.getDgc17()));
            elemRespuesta.setDga17(Integer.parseInt(elemSource.getDga17()));
            elemRespuesta.setDgc18(Integer.parseInt(elemSource.getDgc18()));
            elemRespuesta.setTdg(Integer.parseInt(elemSource.getTdg()));
            elemRespuesta.setCociente(Double.parseDouble(elemSource.getCociente()));
            elemRespuesta.setNumLinea(elemSource.getNumLinea());
            respuesta.add(elemRespuesta);
        }
        return respuesta;
    }

    @Override
    public void validate(CocienteCargaDTO source) throws ExcepcionAplicacion {
    }

    @Override
    public void write(List<CocienteCargaDTO> source) throws ExcepcionAplicacion {
        for (CocienteCargaDTO cocienteCargaDTO : source) {
            EquipoDTO equipo = new EquipoDTO();
            CocienteDTO cociente = new CocienteDTO();
            cociente.setJa15(cocienteCargaDTO.getJa15());
            cociente.setJc16(cocienteCargaDTO.getJc16());
            cociente.setJa16(cocienteCargaDTO.getJa16());
            cociente.setJc17(cocienteCargaDTO.getJc17());
            cociente.setJa17(cocienteCargaDTO.getJa17());
            cociente.setJc18(cocienteCargaDTO.getJc18());
            cociente.setTj(cocienteCargaDTO.getTj());
            cociente.setPa15(cocienteCargaDTO.getPa15());
            cociente.setPc16(cocienteCargaDTO.getPc16());
            cociente.setPa16(cocienteCargaDTO.getPa16());
            cociente.setPc17(cocienteCargaDTO.getPc17());
            cociente.setPa17(cocienteCargaDTO.getPa17());
            cociente.setPc18(cocienteCargaDTO.getPc18());
            cociente.setTp(cocienteCargaDTO.getTp());
            cociente.setDga15(cocienteCargaDTO.getDga15());
            cociente.setDgc16(cocienteCargaDTO.getDgc16());
            cociente.setDga16(cocienteCargaDTO.getDga16());
            cociente.setDgc17(cocienteCargaDTO.getDgc17());
            cociente.setDga17(cocienteCargaDTO.getDga17());
            cociente.setDgc18(cocienteCargaDTO.getDgc18());
            cociente.setTdg(cocienteCargaDTO.getTdg());
            cociente.setCociente(cocienteCargaDTO.getCociente());
            equipo.setCociente(cociente);
            cocienteService.actualizarCociente(equipo);
        }
    }
}
