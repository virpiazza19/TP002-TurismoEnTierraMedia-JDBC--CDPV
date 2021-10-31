package tierraMedia;

public class PromocionAbsoluta extends Promocion {

	private int costoPaquete;

	public PromocionAbsoluta(int ID, TipoAtraccion tipo, String nombre, int costoPaquete) {
		super.ID = ID;
		super.tipoAtraccion = tipo;
		super.nombre = nombre;
		this.costoPaquete = costoPaquete;
	}

	@Override
	protected void costoPromocion() {
		super.costo = this.costoPaquete;
	}
}