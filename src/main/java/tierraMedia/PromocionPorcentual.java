package tierraMedia;

public class PromocionPorcentual extends Promocion {

	private static final int CIEN = 100;
	private double descuento;

	public PromocionPorcentual(int ID, TipoAtraccion tipo, String nombre, double descuento) {
		super.ID = ID;
		super.tipoAtraccion = tipo;
		super.nombre = nombre;
		this.descuento = descuento / CIEN;
	}

	@Override
	protected void costoPromocion() {
		int costo = 0;
		for (Atraccion atraccion : super.atraccionesEnPromocion) {
			costo += atraccion.getCosto();
		}
		super.costo = (int) (costo * (1-this.descuento));
	}

}