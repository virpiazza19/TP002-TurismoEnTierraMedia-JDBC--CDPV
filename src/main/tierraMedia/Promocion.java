package tierraMedia;

public abstract class Promocion extends Producto {
	TipoAtraccion tipoPreferido;
	TipoPromocion tipoPromo;
	int costoPromocion;
	double duracionPromocion;
	protected Atraccion[] atraccionesEnPromocion;

	public Promocion() {
		super();
	}

	public TipoAtraccion getTipoPreferido() {
		return tipoPreferido;
	}

	public TipoPromocion getTipoPromo() {
		return tipoPromo;
	}

	public int getCostoPromocion() {
		return costoPromocion;
	}

	public double getDuracionPromocion() {
		return duracionPromocion;
	}

	public Promocion(String nombre) {
		super.nombre = nombre;
	}

	protected void duracionPromocion() {
		double duracion = 0;
		for (Atraccion A : atraccionesEnPromocion) {
			duracion += A.getDuracion();
		}
		super.duracion = duracion;
	}

	protected abstract void costoPromocion();

	protected Atraccion[] getAtraccionesEnPromocion() {
		return this.atraccionesEnPromocion;
	}

	@Override
	protected boolean esPromo() {
		return true;
	}

	@Override
	public String toString() {
		return "PROMOCION \n Nombre: " + super.getNombre() + " | Atracciones Incluidas: " + this.atraccionesIncluidas()
				+ " | Costo: " + super.getCosto() + " monedas | Duración: " + super.getDuracion() + " horas | Tipo: "
				+ super.getTipoAtraccion();
	}

	private String atraccionesIncluidas() {
		String cadena = "";
		for (int i = 0; i < atraccionesEnPromocion.length - 1; i++) {
			cadena += atraccionesEnPromocion[i].getNombre() + ", ";
		}

		return cadena + atraccionesEnPromocion[atraccionesEnPromocion.length - 1].getNombre();
	}

	@Override
	public boolean contiene(Producto p) {

		for (Atraccion atraccion : atraccionesEnPromocion) {
			if (p.contiene(atraccion)) {
				return true;
			}
		}

		return false;
	}

	
	@Override
	protected boolean hayCupo() {
		for (Atraccion atraccion : atraccionesEnPromocion) {
			if (!atraccion.hayCupo()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	protected void disminuirCupo() throws NoHayCupoException {
		for (Atraccion atraccion : atraccionesEnPromocion) {
			atraccion.disminuirCupo();
		}
	}
}
