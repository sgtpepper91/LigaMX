package ligamx.carga;

import java.io.File;
import java.util.List;
import ligamx.util.ExcepcionAplicacion;
import ligamx.util.LongTaskCarga;

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
     * @param task
     * @throws ExcepcionAplicacion
     */
    void leerArchivo(File file, LongTaskCarga task) throws ExcepcionAplicacion;

    /**
     *
     * @param source
     * @return
     * @throws ExcepcionAplicacion
     */
    List<U> converter(List<T> source) throws ExcepcionAplicacion;
    
    /**
     * 
     * @param source
     * @throws ExcepcionAplicacion 
     */
    void validate(U source) throws ExcepcionAplicacion;
    
        /**
     * 
     * @param source
     * @throws ExcepcionAplicacion 
     */
    void write(List<U> source) throws ExcepcionAplicacion;
}
