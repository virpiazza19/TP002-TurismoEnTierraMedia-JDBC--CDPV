package tierraMedia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PromocionAbsolutaTest {
	PromocionAbsoluta pa3;
	PromocionAbsoluta pa2;
	Atraccion a1;
	Atraccion a2;
	Atraccion a3;

	@Before
	public void setUp() {
		a1 = new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA);
		a2 = new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA);
		a3 = new Atraccion("Bosque Negro", 3, 4, 12, TipoAtraccion.AVENTURA);
		pa3 = new PromocionAbsoluta(TipoAtraccion.AVENTURA, "Promo 3 Atracciones", a1, a2, a3, 30);
		pa2 = new PromocionAbsoluta(TipoAtraccion.AVENTURA, "Promo 2 Atracciones", a1, a2, 20);
	}

	@Test
	public void creacionDePromosTest() {
		assertNotNull(pa3);
		assertNotNull(pa2);
	}

	@Test
	public void costoPromocionTest() {
		assertEquals(30, pa3.getCosto());
		assertEquals(20, pa2.getCosto());
	}

	@Test
	public void duracionPromoTest() {
		double duracion3atracciones = a1.getDuracion() + a2.getDuracion() + a3.getDuracion();
		double duracion2atracciones = a1.getDuracion() + a2.getDuracion();
		assertEquals(duracion3atracciones, pa3.getDuracion(), 0);
		assertEquals(duracion2atracciones, pa2.getDuracion(), 0);
	}

	@Test
	public void nombrePromoTest() {
		assertEquals("Promo 3 Atracciones", pa3.getNombre());
		assertEquals("Promo 2 Atracciones", pa2.getNombre());
	}
}
