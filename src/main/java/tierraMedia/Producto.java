package tierraMedia;

import java.util.ArrayList;
import java.util.List;

public abstract class Producto {

	protected List<Atraccion> atracciones = new ArrayList<Atraccion>();
	protected List<Promocion> promociones = new ArrayList<Promocion>();
	protected int id;
	protected TipoAtraccion tipoAtraccion;
	protected String nombre;
	protected int costo;
	protected double duracion;
	
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public int getCosto() {
		return costo;
	}

	public double getDuracion() {
		return duracion;
	}
	public TipoAtraccion getTipoAtraccion() {
		return tipoAtraccion;
	}
	
	protected boolean esPromo() {
		return false;
	}

	public void agregarAtraccion(Atraccion a) {
		this.atracciones.add(a);
	}
	
	public void agregarPromocion(Promocion p) {
		this.promociones.add(p);
	}
	
	protected abstract boolean contiene(Producto p);
	
	protected abstract boolean hayCupo();

	protected abstract void disminuirCupo() throws NoHayCupoException;
}