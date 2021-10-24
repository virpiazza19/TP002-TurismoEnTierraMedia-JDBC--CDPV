package tierraMedia;

public class PromocionAxB extends Promocion {

	private Atraccion atraccionBonificada;

	public PromocionAxB(TipoAtraccion tipo, String nombre, Atraccion a1, Atraccion a2, Atraccion atraccionBonificada) {
		super.tipoAtraccion = tipo;
		super.nombre = nombre;
		super.atraccionesEnPromocion = new Atraccion[] { a1, a2, atraccionBonificada };
		super.duracionPromocion();
		this.atraccionBonificada = atraccionBonificada;
		this.costoPromocion();
	}

	public PromocionAxB(TipoAtraccion tipo, String nombre, Atraccion a1, Atraccion atraccionBonificada) {
		super.tipoAtraccion = tipo;
		super.nombre = nombre;
		super.atraccionesEnPromocion = new Atraccion[] { a1, atraccionBonificada };
		super.duracionPromocion();
		this.atraccionBonificada = atraccionBonificada;
		this.costoPromocion();
	}

	@Override
	protected void costoPromocion() {
		int costo = 0;
		for (int i = 0; i < super.atraccionesEnPromocion.length - 1; i++) {
			costo += super.atraccionesEnPromocion[i].costo;
		}
		super.costo = costo;
	}

	protected Atraccion getAtraccionBonificada() {
		return this.atraccionBonificada;
	}
}
