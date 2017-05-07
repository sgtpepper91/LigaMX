package ligamx.service.impl;

import java.io.File;
import ligamx.util.ExcepcionAplicacion;
import ligamx.carga.JugadorReaderServiceImpl;
import ligamx.carga.ReaderService;
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
    

    @Test
    public void carga() {
        try {
            File file = new File("jugadores.txt");
            reader = new JugadorReaderServiceImpl();
            reader.leerArchivo(file);
        } catch (ExcepcionAplicacion ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
