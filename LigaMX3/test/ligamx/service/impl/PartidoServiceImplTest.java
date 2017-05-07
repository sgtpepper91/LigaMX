package ligamx.service.impl;

import java.util.List;
import ligamx.dto.EquipoDTO;
import ligamx.dto.PartidoDTO;
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
public class PartidoServiceImplTest {
    
    public PartidoServiceImplTest() {
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
     * Test of insertarPartido method, of class PartidoServiceImpl.
     */
    @Test
    public void testInsertarPartido() throws Exception {
        System.out.println("insertarPartido");
        PartidoDTO partido = null;
        PartidoServiceImpl instance = new PartidoServiceImpl();
        boolean expResult = false;
        boolean result = instance.insertarPartido(partido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarPartido method, of class PartidoServiceImpl.
     */
    @Test
    public void testActualizarPartido() throws Exception {
        System.out.println("actualizarPartido");
        PartidoDTO partido = null;
        PartidoServiceImpl instance = new PartidoServiceImpl();
        boolean expResult = false;
        boolean result = instance.actualizarPartido(partido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPartidosporEquipo method, of class PartidoServiceImpl.
     */
    @Test
    public void testBuscarPartidosporEquipo() throws Exception {
        System.out.println("buscarPartidosporEquipo");
        EquipoDTO equipo = null;
        PartidoServiceImpl instance = new PartidoServiceImpl();
        List<PartidoDTO> expResult = null;
        List<PartidoDTO> result = instance.buscarPartidosporEquipo(equipo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPartidosporJornada method, of class PartidoServiceImpl.
     */
    @Test
    public void testBuscarPartidosporJornada() throws Exception {
        System.out.println("buscarPartidosporJornada");
        Integer jornada = null;
        PartidoServiceImpl instance = new PartidoServiceImpl();
        List<PartidoDTO> expResult = null;
        List<PartidoDTO> result = instance.buscarPartidosporJornada(jornada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPartidosporId method, of class PartidoServiceImpl.
     */
    @Test
    public void testBuscarPartidosporId() throws Exception {
        System.out.println("buscarPartidosporId");
        Integer id = null;
        PartidoServiceImpl instance = new PartidoServiceImpl();
        PartidoDTO expResult = null;
        PartidoDTO result = instance.buscarPartidosporId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
