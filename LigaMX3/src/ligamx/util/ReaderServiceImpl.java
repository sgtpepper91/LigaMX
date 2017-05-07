package ligamx.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author hector.lopez
 * @param <T>
 * @param <U>
 */
public abstract class ReaderServiceImpl<T extends BaseLecturaDTO, U extends BaseCargaDTO> implements ReaderService<T, U> {

    protected static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private static final String SEPARADOR = "\t";

    /**
     *
     * @param file
     * @return
     * @throws ExcepcionAplicacion
     */
    @Override
    public List<T> leerArchivo(File file) throws ExcepcionAplicacion {
        List<T> source = null;
        if (!file.canRead()) {
            throw new ExcepcionAplicacion("El arhivo no se puede leer");
        }
        try {
            LOGGER.debug("Leyendo archivo " + file.getName());
            source = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String sLineaActual;
            String[] linea;
            br.readLine();
            Integer numLinea = 2;
            while ((sLineaActual = br.readLine()) != null) {
                linea = sLineaActual.split(SEPARADOR);
                T baseDTO = getInstanceofT();
                baseDTO.setNumLinea(numLinea);
                if (null != baseDTO.getMapper() && baseDTO.getMapper().length > 0) {
                    for (int i = 0; i < baseDTO.getMapper().length; i++) {
                        String mapper = baseDTO.getMapper()[i];
                        String nombre = "set" + mapper.substring(0, 1).toUpperCase() + mapper.substring(1);
                        Method setMethod = baseDTO.getClass().getMethod(nombre, String.class);
                        setMethod.invoke(baseDTO, i == linea.length && sLineaActual.endsWith(SEPARADOR) ? "" : linea[i]);
                    }
                    source.add(baseDTO);
                } else {
                    throw new ExcepcionAplicacion("Error con el mapper");
                }
            }

        } catch (IOException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            LOGGER.error(ex);
            throw new ExcepcionAplicacion(ex.getMessage());
        }
        return source;
    }

    private T getInstanceofT() throws ExcepcionAplicacion {
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> type = (Class<T>) superClass.getActualTypeArguments()[0];
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new ExcepcionAplicacion(ex.getMessage());
        }
    }
}
