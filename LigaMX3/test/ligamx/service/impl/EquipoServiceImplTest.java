package ligamx.service.impl;

import java.util.List;
import ligamx.dto.EquipoDTO;
import ligamx.dto.GolDTO;
import ligamx.util.EquiposEnum;
import ligamx.util.ExcepcionAplicacion;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hecto
 */
public class EquipoServiceImplTest {

    public EquipoServiceImplTest() {
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

    /**
     * Test of insertarEquipo method, of class EquipoServiceImpl.
     *
     * @throws ligamx.util.ExcepcionAplicacion
     */
    @Test
    public void testInsertarEquipo() throws ExcepcionAplicacion {
        EquipoServiceImpl instance = new EquipoServiceImpl();
        System.out.println("insertarEquipo");
        EquiposEnum[] equipoArray = EquiposEnum.values();
        for (EquiposEnum equipoEnum : equipoArray) {
            EquipoDTO equipo = new EquipoDTO();
            equipo.setIdEquipo(equipoEnum.getId());
            equipo.setNombre(equipoEnum.getNombre());
            boolean result = instance.insertarEquipo(equipo);
            if (result) {
                System.out.println(equipoEnum.getId() + " - " +  equipoEnum.getNombre() + " insertado");
            } else {
                System.out.println(equipoEnum.getId() + " - " +  equipoEnum.getNombre() + " falló");
            }
        }
    }

    /**
     * Test of buscarEquipos method, of class EquipoServiceImpl.
     */
    @Test
    public void testBuscarEquipos() throws Exception {
        System.out.println("buscarEquipos");
        EquipoServiceImpl instance = new EquipoServiceImpl();
        List<EquipoDTO> expResult = null;
        List<EquipoDTO> result = instance.buscarEquipos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarEquipoporNombre method, of class EquipoServiceImpl.
     */
    @Test
    public void testBuscarEquipoporNombre() throws Exception {
        System.out.println("buscarEquipoporNombre");
        String nombre = "";
        EquipoServiceImpl instance = new EquipoServiceImpl();
        EquipoDTO expResult = null;
        EquipoDTO result = instance.buscarEquipoporNombre(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarEquipoporId method, of class EquipoServiceImpl.
     */
    @Test
    public void testBuscarEquipoporId() throws Exception {
        System.out.println("buscarEquipoporId");
        Integer id = null;
        EquipoServiceImpl instance = new EquipoServiceImpl();
        EquipoDTO expResult = null;
        EquipoDTO result = instance.buscarEquipoporId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarEquipo method, of class EquipoServiceImpl.
     */
    @Test
    public void testActualizarEquipo() throws Exception {
        System.out.println("actualizarEquipo");
        GolDTO gol = null;
        Integer idPartido = null;
        EquipoServiceImpl instance = new EquipoServiceImpl();
        boolean expResult = false;
        //boolean result = instance.actualizarEquipo(gol, idPartido);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
