package ligamx.util;

import java.io.File;
import java.util.List;

/**
 *
 * @author hecto
 * @param <T>
 * @param <U>
 */
public interface ReaderService <T extends BaseLecturaDTO, U extends BaseCargaDTO> {

    /**
     *
     * @param file
     * @return
     * @throws ExcepcionAplicacion
     */
    List<T>  leerArchivo(File file) throws ExcepcionAplicacion;

    /**
     *
     * @param source
     * @return
     * @throws ExcepcionAplicacion
     */
    List<U> converter(List<T> source) throws ExcepcionAplicacion;
    
    List<U> validate(List<U> source) throws ExcepcionAplicacion;
}
