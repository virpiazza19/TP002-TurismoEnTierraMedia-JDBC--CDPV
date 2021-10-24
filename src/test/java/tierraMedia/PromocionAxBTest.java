package tierraMedia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PromocionAxBTest {

	PromocionAxB p3x2;
	PromocionAxB p2x1;
	Atraccion a1;
	Atraccion a2;
	Atraccion aBonificada;

	@Before
	public void setUp() {
		a1 = new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA);
		a2 = new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA);
		aBonificada = new Atraccion("Bosque Negro", 3, 4, 12, TipoAtraccion.AVENTURA);
		p3x2 = new PromocionAxB(TipoAtraccion.AVENTURA, "Promo 3 Atracciones por el Precio de 2", a1, a2, aBonificada);
		p2x1 = new PromocionAxB(TipoAtraccion.AVENTURA, "Promo 2 Atracciones por el Precio de 1", a1, aBonificada);
	}

	@Test
	public void crearPromosTest() {
		assertNotNull(p3x2);
		assertNotNull(p2x1);
	}

	@Test
	public void costoPromoTest() {
		int costo2Atracciones = a1.getCosto() + a2.getCosto();
		assertEquals(costo2Atracciones, p3x2.getCosto());

		int costo1Atracciones = a1.getCosto();
		assertEquals(costo1Atracciones, p2x1.getCosto());
	}

	@Test
	public void duracionPromoTest() {
		double duracion3atracciones = a1.getDuracion() + a2.getDuracion() + aBonificada.getDuracion();
		assertEquals(duracion3atracciones, p3x2.getDuracion(),0);
		
		double duracion2atracciones = a1.getDuracion() + aBonificada.getDuracion();
		assertEquals(duracion2atracciones, p2x1.getDuracion(),0);
	}
	
	@Test
	public void nombrePromosTest() {
		assertEquals("Promo 3 Atracciones por el Precio de 2", p3x2.getNombre());
		assertEquals("Promo 2 Atracciones por el Precio de 1", p2x1.getNombre());
	}
}
