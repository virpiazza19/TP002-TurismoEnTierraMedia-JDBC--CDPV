package tierraMedia;

import java.util.List;

public class Usuario {

	private int id;
	private String nombre;
	private int presupuesto;
	private double tiempoDisponible;
	private TipoAtraccion atraccionPreferida;
	protected Itinerario itinerario = new Itinerario();

	public Usuario(int id, String nombre, int presupuesto, double tiempoDisponible, TipoAtraccion atraccionPreferida) {
		this.id = id;
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.atraccionPreferida = atraccionPreferida;
	}
	
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public TipoAtraccion getAtraccionPreferida() {
		return atraccionPreferida;
	}

	public List<Producto> getProductosEnItinerario() {
		return this.itinerario.productos;
	}

	public void agregarProductosAlItinerario(Producto producto) {
		itinerario.agregarProductos(producto);
		this.presupuesto -= producto.getCosto();
		this.tiempoDisponible -= producto.getDuracion();
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", presupuesto=" + presupuesto + ", tiempoDisponible=" + tiempoDisponible
				+ ", atraccionPreferida=" + atraccionPreferida + "]";
	}



}