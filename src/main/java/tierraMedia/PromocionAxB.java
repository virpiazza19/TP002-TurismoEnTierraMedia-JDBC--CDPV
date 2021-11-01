package tierraMedia;

import enums.TipoAtraccion;

public class PromocionAxB extends Promocion {

	private Atraccion atraccionBonificada;

	public PromocionAxB(int ID, TipoAtraccion tipo, String nombre) {
		super.ID = ID;
		super.tipoAtraccion = tipo;
		super.nombre = nombre;
	}

	@Override
	protected void costoPromocion() {
		int costo = 0;
		for (Atraccion atraccion : super.atraccionesEnPromocion) {
			costo += atraccion.getCosto();
		}
		super.costo = costo - super.atraccionesEnPromocion.get(atraccionesEnPromocion.size()-1).getCosto();
	}

	protected Atraccion getAtraccionBonificada() {
		return this.atraccionBonificada;
	}
}
