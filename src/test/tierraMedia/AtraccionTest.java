package tierraMedia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AtraccionTest {
    Atraccion a;
	
	@Before
	public void setUp() {
		a = new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA);
	}
    
	@Test
	public void creacionDeAtraccionTest() {
		assertNotNull(a);
	}
	
	@Test
	public void verificandoGettersTest() {
		assertEquals("AVENTURA", a.tipoAtraccion.name());
		assertEquals("Moria", a.getNombre());
		assertEquals(10, a.getCosto());
		assertEquals(2, a.getDuracion(),0);
		assertEquals(6, a.getCupo());
	}
	
	@Test
	public void diminuirCupoTest() throws NoHayCupoException {
		a.disminuirCupo();
		assertEquals(5, a.getCupo());
	}

	
	// falta verificar exception
}
