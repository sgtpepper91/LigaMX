package ligamx.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import ligamx.util.ExcepcionAplicacion;
import ligamx.util.JugadorCargaDTO;
import ligamx.util.JugadorLecturaDTO;
import ligamx.util.JugadorReaderServiceImpl;
import ligamx.util.ReaderService;
import ligamx.util.ValidarAnotaciones;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author hecto
 */
public class TestCarga {

    public TestCarga() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    private ReaderService reader;
    private ValidarAnotaciones<JugadorLecturaDTO> validar;

    @Test
    public void carga() {
        try {
            File file = new File("jugadores.txt");
            reader = new JugadorReaderServiceImpl();
            List<JugadorLecturaDTO> source = reader.leerArchivo(file);
            validar = new ValidarAnotaciones<>();
            validar.validarAnotaciones(source);
            List<JugadorLecturaDTO> prev = new ArrayList<>();
            for (JugadorLecturaDTO jugadorLecturaDTO : source) {
                if (jugadorLecturaDTO.getMensajes().isEmpty()) {
                    prev.add(jugadorLecturaDTO);
                } else {
                    System.out.println(jugadorLecturaDTO.toString());
                }
            }
            List<JugadorCargaDTO> cargaDTO = reader.converter(prev);
            System.out.println("cargaDTO= " + cargaDTO.toString());
        } catch (ExcepcionAplicacion ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
