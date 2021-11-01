package tierraMedia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PromocionPorcentualTest {

	PromocionPorcentual p3dto;
	PromocionPorcentual p2dto;
	Atraccion a1;
	Atraccion a2;
	Atraccion a3;

	@Before
	public void setUp() {
		a1 = new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA);
		a2 = new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA);
		a3 = new Atraccion("Bosque Negro", 3, 4, 12, TipoAtraccion.AVENTURA);
		p3dto = new PromocionPorcentual(TipoAtraccion.AVENTURA, "Promo 3 Atracciones con descuento", a1, a2, a3, 30);
		p2dto = new PromocionPorcentual(TipoAtraccion.AVENTURA, "Promo 2 Atracciones con descuento", a1, a2, 50);
	}

	@Test
	public void crearPromosTest() {
		assertNotNull(p3dto);
		assertNotNull(p2dto);
	}

	@Test
	public void costoPromoTest() {
		int costoEsperado3atracciones = (int) ((10 + 25 + 3) * 0.7);
		assertEquals(costoEsperado3atracciones, p3dto.getCosto());

		int costoEsperado2atracciones = (int) ((10 + 25) * 0.5);
		assertEquals(costoEsperado2atracciones, p2dto.getCosto());
	}

	@Test
	public void duracionPromoTest() {
		double duracion3atracciones = a1.getDuracion() + a2.getDuracion() + a3.getDuracion();
		assertEquals(duracion3atracciones, p3dto.getDuracion(), 0);

		double duracion2atracciones = 2 + 3;
		assertEquals(duracion2atracciones, p2dto.getDuracion(), 0);
	}

	@Test
	public void nombrePromosTest() {
		assertEquals("Promo 3 Atracciones con descuento", p3dto.getNombre());
		assertEquals("Promo 2 Atracciones con descuento", p2dto.getNombre());
	}
}
