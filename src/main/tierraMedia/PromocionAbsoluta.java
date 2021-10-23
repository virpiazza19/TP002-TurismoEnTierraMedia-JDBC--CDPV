package tierraMedia;

public class PromocionAbsoluta extends Promocion {

	private int costoPaquete;

	public PromocionAbsoluta(TipoAtraccion tipo, String nombre, Atraccion a1, Atraccion a2, int costoPaquete) {
		super.tipoAtraccion = tipo;
		super.nombre = nombre;
		super.atraccionesEnPromocion = new Atraccion[] { a1, a2 };
		super.duracionPromocion();
		this.costoPaquete = costoPaquete;
		this.costoPromocion();
	}

	public PromocionAbsoluta(TipoAtraccion tipo, String nombre, Atraccion a1, Atraccion a2, Atraccion a3, int costoPaquete) {
		super.tipoAtraccion = tipo;
		super.nombre = nombre;
		super.atraccionesEnPromocion = new Atraccion[] { a1, a2, a3 };
		super.duracionPromocion();
		this.costoPaquete = costoPaquete;
		this.costoPromocion();
	}

	@Override
	protected void costoPromocion() {
		super.costo = this.costoPaquete;
	}
}
