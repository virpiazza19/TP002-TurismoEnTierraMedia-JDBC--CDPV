package tierraMedia;

import java.util.ArrayList;
import java.util.List;

public class Itinerario {
	protected List<Producto> productos = new ArrayList<Producto>();
	private String usuario;
	private String atraccion;
	private String promo;
	
	public Itinerario() {
	}

	public Itinerario(String usuario, String atraccion, String promo) {
		this.usuario = usuario;
		this.atraccion = atraccion;
		this.promo = promo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getAtraccion() {
		return atraccion;
	}

	public void setAtraccion(String atraccion) {
		this.atraccion = atraccion;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	protected void agregarProductos(Producto p) {
		this.productos.add(p);
	}

	protected double duracionTotal() {
		double horas = 0.0;

		for (Producto producto : productos) {
			horas += producto.getDuracion();
		}

		return horas;
	}

	protected int costoTotal() {
		int monedas = 0;

		for (Producto producto : productos) {
			monedas += producto.getCosto();
		}

		return monedas;
	}

	@Override
	public String toString() {
		String cadena = "";

		for (Producto producto : productos) {
			cadena += producto + "\n";
		}

		return cadena;
	}
}
