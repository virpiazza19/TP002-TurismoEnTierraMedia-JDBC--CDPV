package tierraMedia;

import java.util.List;

import enums.TipoAtraccion;
import enums.TipoPromocion;
import excepciones.NoHayCupoException;

public abstract class Promocion extends Producto {
	int ID;
	TipoAtraccion tipoPreferido;
	TipoPromocion tipoPromo;
	int costoPromocion;
	double duracionPromocion;
	protected List<Atraccion> atraccionesEnPromocion;

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

	public void setAtraccionesEnPromocion(List<Atraccion> atracciones) {
		this.atraccionesEnPromocion = atracciones; 
		this.duracionPromocion();
		costoPromocion();
	}
	
	protected void duracionPromocion() {
		double duracion = 0;
		for (Atraccion A : atraccionesEnPromocion) {
			duracion += A.getDuracion();
		}
		super.duracion = duracion;
	}

	protected abstract void costoPromocion();

	protected List<Atraccion> getAtraccionesEnPromocion() {
		return this.atraccionesEnPromocion;
	}

	@Override
	public boolean esPromo() {
		return true;
	}

	@Override
	public String toString() {
		return "\nPROMOCION \n Nombre: " + super.getNombre() + " | Atracciones Incluidas: " + this.atraccionesIncluidas()
		+ " | Duraci�n: " + super.getDuracion() + " | Costo: " + getCosto();
				
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	private String atraccionesIncluidas() {
		String cadena = "";
		for (Atraccion atraccion : atraccionesEnPromocion) {
			cadena += atraccion.getNombre() + ". ";
		}

		return cadena;
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
