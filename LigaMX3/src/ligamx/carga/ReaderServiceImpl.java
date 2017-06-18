package ligamx.carga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import javax.swing.JOptionPane;
import ligamx.util.ExcepcionAplicacion;
import ligamx.util.LongTaskCarga;

/**
 *
 * @author hector.lopez
 * @param <T>
 * @param <U>
 */
public abstract class ReaderServiceImpl<T extends BaseLecturaDTO, U extends BaseCargaDTO> implements ReaderService<T, U> {

    protected static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private static final String SEPARADOR = "\t";
    private final ValidarAnotaciones<T> validar = new ValidarAnotaciones<>();

    /**
     *
     * @param file
     * @param task
     * @throws ExcepcionAplicacion
     */
    @Override
    public void leerArchivo(File file, LongTaskCarga task) throws ExcepcionAplicacion {
        if (!file.canRead()) {
            throw new ExcepcionAplicacion("El arhivo no se puede leer");
        }
        try {
            LOGGER.debug("Leyendo archivo " + file.getName());
            List<T> source = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String sLineaActual;
            String[] linea;
            br.readLine();
            Integer numLinea = 2;
            while ((sLineaActual = br.readLine()) != null) {
                linea = sLineaActual.split(SEPARADOR);
                T baseDTO = getInstanceofT();
                baseDTO.setNumLinea(numLinea++);
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

            validar.validarAnotaciones(source);
            List<U> uList = this.convertir(source);
            this.validarRegistros(uList);
            this.escribir(uList);
            task.setCurrent(1);
            this.mostrarMensajeFinal(source, uList);
        } catch (IOException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            LOGGER.error(ex);
            throw new ExcepcionAplicacion(ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private T getInstanceofT() throws ExcepcionAplicacion {
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> type = (Class<T>) superClass.getActualTypeArguments()[0];
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new ExcepcionAplicacion(ex.getMessage());
        }
    }

    private List<U> convertir(List<T> source) throws ExcepcionAplicacion {
        List<T> prev = new ArrayList<>();
        for (T elemSource : source) {
            if (elemSource.getMensajes().isEmpty()) {
                prev.add(elemSource);
            } else {
                System.out.println(elemSource.toString());
            }
        }
        return this.converter(prev);
    }

    private void validarRegistros(List<U> source) throws ExcepcionAplicacion {
        for (U u : source) {
            this.validate(u);
        }
    }

    private void escribir(List<U> uList) throws ExcepcionAplicacion {
        List<U> val = new ArrayList<>();
        for (U u : uList) {
            if (u.getMensajes().isEmpty()) {
                val.add(u);
            }
        }
        this.write(val);
    }

    private void mostrarMensajeFinal(List<T> source, List<U> uList) {
        StringBuilder incorrectosPrev = new StringBuilder();
        StringBuilder incorrectosVal = new StringBuilder();
        StringBuilder correctos = new StringBuilder();
        for (T t : source) {
            if (!t.getMensajes().isEmpty()) {
                incorrectosPrev.append(t.getNumLinea()).append(t.imprimirMensajes());
            }
        }
        for (U u : uList) {
            if (!u.getMensajes().isEmpty()) {
                incorrectosVal.append(u.getNumLinea()).append(u.imprimirMensajes());
            } else {
                correctos.append(u.getNumLinea()).append("\n");
            }
        }
        ResultadoCarga resultadoCarga = new ResultadoCarga();
        resultadoCarga.getTxtIncorrectosPrev().setText(incorrectosPrev.toString());
        resultadoCarga.getTxtIncorrectosVal().setText(incorrectosVal.toString());
        resultadoCarga.getTxtCorrectos().setText(correctos.toString());
        JOptionPane.showMessageDialog(null, resultadoCarga, "Resultado carga masiva", JOptionPane.PLAIN_MESSAGE);
    }

}
